package com.example.silenteight.algorithm;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MajorityRuleStrategyTest {

    private final MajorityRuleStrategy strategy = new MajorityRuleStrategy();

    @Test
    void shouldSplitFullName() {
        String fullName = "Jan Maria Rokita";

        List<String> names = strategy.splitFullName(fullName);

        assertEquals(3, names.size());
    }

    @Test
    void shouldCountNameOccurrences() {
        List<String> splitFullName = getSplitFullName();
        String maleNames = getMaleNames();

        InputStream inputStream = new ByteArrayInputStream(maleNames.getBytes(StandardCharsets.UTF_8));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        long occurrences = strategy.countNameOccurrences(splitFullName, bufferedReader);

        assertEquals(2, occurrences);
    }

    private List<String> getSplitFullName() {
        return List.of("jan", "maria", "rokita");
    }

    private String getMaleNames() {
        return "Jan" +
                System.lineSeparator() +
                "Maria" +
                System.lineSeparator() +
                "Robert";
    }
}