package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        name = names[0];
        return name;
    }

    private void checkGender(String name) {
        InputStream inputMale = getClass().getResourceAsStream("/male");
        InputStream inputFemale = getClass().getResourceAsStream("/female");

        try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
             BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

            isMale = bufferedReaderMale
                    .lines()
                    .anyMatch(nameInFile -> nameInFile.equals(name));

            isFemale = bufferedReaderFemale
                    .lines()
                    .anyMatch(nameInFile -> nameInFile.equals(name));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
