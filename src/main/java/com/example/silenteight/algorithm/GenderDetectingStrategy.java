package com.example.silenteight.algorithm;

import com.example.silenteight.domain.Gender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.example.silenteight.Application.*;
import static com.example.silenteight.domain.Gender.*;

public abstract class GenderDetectingStrategy {

    protected boolean isMale;
    protected boolean isFemale;

    protected abstract void performAlgorithm(BufferedReader bufferedReaderMale, BufferedReader bufferedReaderFemale, String fullName);

    public Gender detectGenderByName(String fullName) throws IOException {
        checkGender(fullName);
        return getGender();
    }

    public void checkGender(String name) throws IOException {
        try (JarFile jarFile = new JarFile(JARFILE)) {
            JarEntry maleFileEntry = jarFile.getJarEntry(MALENAMESFILE);
            JarEntry femaleFileEntry = jarFile.getJarEntry(FEMALENAMESFILE);

            InputStream inputMale = jarFile.getInputStream(maleFileEntry);
            InputStream inputFemale = jarFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                performAlgorithm(bufferedReaderMale, bufferedReaderFemale, name);

            }
        }
    }

    public Gender getGender() {
        if (isFemale) {
            return FEMALE;
        }
        if (isMale) {
            return MALE;
        }
        return INCONCLUSIVE;
    }

}
