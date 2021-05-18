package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SingleTokenNameStrategy extends GenderDetectingStrategy {

    private boolean isMale;
    private boolean isFemale;

    @Override
    public String detectGenderByName(String fullName) {
        String firstname = getFirstname(fullName);
        checkGender(firstname);

        if (isMale && isFemale) {
            return INCONCLUSIVE;
        }
        if (isFemale) {
            return FEMALE;
        }
        if (isMale) {
            return MALE;
        }

        return INCONCLUSIVE;
    }

    private String getFirstname(String fullName) {
        String[] names = fullName.split("\\s+");
        return names[0].toLowerCase();
    }

    private void checkGender(String name) {
        try (JarFile jarFile = new JarFile(JARFILE)) {
            JarEntry maleFileEntry = jarFile.getJarEntry(MALENAMESFILE);
            JarEntry femaleFileEntry = jarFile.getJarEntry(FEMALENAMESFILE);

            InputStream inputMale = jarFile.getInputStream(maleFileEntry);
            InputStream inputFemale = jarFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                isMale = bufferedReaderMale
                        .lines()
                        .map(String::toLowerCase)
                        .anyMatch(nameInFile -> nameInFile.equals(name));

                isFemale = bufferedReaderFemale
                        .lines()
                        .map(String::toLowerCase)
                        .anyMatch(nameInFile -> nameInFile.equals(name));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}