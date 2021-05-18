package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class MajorityRuleStrategy implements GuessStrategy {

    private long maleCounter;
    private long femaleCounter;

    @Override
    public String guessGender(String fullName) {
        checkGender(fullName);

        if (maleCounter == femaleCounter) {
            return "INCONCLUSIVE";
        }
        if (maleCounter > femaleCounter) {
            return "MALE";
        }
        return "FEMALE";
    }

    private void checkGender(String fullName) {
        List<String> names = List.of(fullName.split("\\s+"))
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        try (JarFile javaFile = new JarFile("tokens.jar")) {
            JarEntry maleFileEntry = javaFile.getJarEntry("male");
            JarEntry femaleFileEntry = javaFile.getJarEntry("female");

            InputStream inputMale = javaFile.getInputStream(maleFileEntry);
            InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                maleCounter = bufferedReaderMale
                        .lines()
                        .map(String::toLowerCase)
                        .filter(names::contains)
                        .count();

                femaleCounter = bufferedReaderFemale
                        .lines()
                        .map(String::toLowerCase)
                        .filter(names::contains)
                        .count();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
