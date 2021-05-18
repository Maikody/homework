package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class OneTokenStrategy implements GuessStrategy {
    private boolean isMale;
    private boolean isFemale;

    @Override
    public String guessGender(String fullName) {
        String firstname = getFirstname(fullName);
        checkGender(firstname);

        if (isMale && isFemale) {
            return "INCONCLUSIVE";
        }
        if (isFemale) {
            return "FEMALE";
        }
        if (isMale) {
            return "MALE";
        }

        return "INCONCLUSIVE";
    }

    private String getFirstname(String name) {
        String[] names = name.split("\\s+");
        name = names[0].toLowerCase();
        return name;
    }

    private void checkGender(String name) {
        try (JarFile javaFile = new JarFile("tokens.jar")) {
            JarEntry maleFileEntry = javaFile.getJarEntry("male");
            JarEntry femaleFileEntry = javaFile.getJarEntry("female");

            InputStream inputMale = javaFile.getInputStream(maleFileEntry);
            InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

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
