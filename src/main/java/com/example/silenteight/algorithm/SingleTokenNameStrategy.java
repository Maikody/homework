package com.example.silenteight.algorithm;

import java.io.BufferedReader;

public class SingleTokenNameStrategy extends GenderDetectingStrategy {

    @Override
    protected void performAlgorithm(BufferedReader bufferedReaderMale, BufferedReader bufferedReaderFemale, String fullName) {
        String firstname = getFirstname(fullName);
        isMale = checkNameExistence(firstname, bufferedReaderMale);
        isFemale = checkNameExistence(firstname, bufferedReaderFemale);
    }

    public String getFirstname(String fullName) {
        String[] names = fullName.trim().split("\\s+");
        return names[0];
    }

    public boolean checkNameExistence(String name, BufferedReader bufferedReader) {
        return bufferedReader
                .lines()
                .map(String::toLowerCase)
                .anyMatch(nameInFile -> nameInFile.equals(name.toLowerCase()));
    }
}
