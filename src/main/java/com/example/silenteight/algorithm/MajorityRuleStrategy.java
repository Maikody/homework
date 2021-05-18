package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
        List<String> names = List.of(fullName.split("\\s+"));

        InputStream inputMale = getClass().getResourceAsStream("/male");
        InputStream inputFemale = getClass().getResourceAsStream("/female");

        try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
             BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

            maleCounter = bufferedReaderMale
                    .lines()
                    .filter(names::contains)
                    .count();

            femaleCounter = bufferedReaderFemale
                    .lines()
                    .filter(names::contains)
                    .count();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
