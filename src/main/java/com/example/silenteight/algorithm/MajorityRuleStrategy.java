package com.example.silenteight.algorithm;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

public class MajorityRuleStrategy extends GenderDetectingStrategy {

    @Override
    protected void performAlgorithm(BufferedReader bufferedReaderMale, BufferedReader bufferedReaderFemale, String fullName) {
        List<String> names = splitFullName(fullName);
        long maleCounter = countNameOccurrences(names, bufferedReaderMale);
        long femaleCounter = countNameOccurrences(names, bufferedReaderFemale);
        isMale = maleCounter > femaleCounter;
        isFemale = femaleCounter > maleCounter;
    }

    public List<String> splitFullName(String fullName) {
        return List.of(fullName.split("\\s+"))
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public long countNameOccurrences(List<String> names, BufferedReader bufferedReaderMale) {
        return bufferedReaderMale
                .lines()
                .map(String::toLowerCase)
                .filter(names::contains)
                .count();
    }
}
