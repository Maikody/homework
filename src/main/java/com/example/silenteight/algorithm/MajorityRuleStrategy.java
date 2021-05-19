package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class MajorityRuleStrategy extends GenderDetectingStrategy {

    private long maleCounter;
    private long femaleCounter;

    @Override
    public String detectGenderByName(String fullName) throws IOException {
        checkGender(fullName);
        return getGender();
    }

    private String getGender() {
        if (maleCounter == femaleCounter) {
            return INCONCLUSIVE;
        }
        if (maleCounter > femaleCounter) {
            return MALE;
        }
        return FEMALE;
    }

    private void checkGender(String fullName) throws IOException {
        try (JarFile javaFile = new JarFile(JARFILE)) {
            JarEntry maleFileEntry = javaFile.getJarEntry(MALENAMESFILE);
            JarEntry femaleFileEntry = javaFile.getJarEntry(FEMALENAMESFILE);

            InputStream inputMale = javaFile.getInputStream(maleFileEntry);
            InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                List<String> names = splitFullName(fullName);

                maleCounter = countNameOccurrencesInStream(bufferedReaderMale, names);

                femaleCounter = countNameOccurrencesInStream(bufferedReaderFemale, names);
            }
        }
    }

    private long countNameOccurrencesInStream(BufferedReader bufferedReaderMale, List<String> names) {
        return bufferedReaderMale
                .lines()
                .map(String::toLowerCase)
                .filter(names::contains)
                .count();
    }

    private List<String> splitFullName(String fullName) {
        return List.of(fullName.split("\\s+"))
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
