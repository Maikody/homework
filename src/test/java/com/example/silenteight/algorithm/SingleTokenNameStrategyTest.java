package com.example.silenteight.algorithm;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleTokenNameStrategyTest {

    private final SingleTokenNameStrategy strategy = new SingleTokenNameStrategy();

    @Test
    void shouldGetFirstName() {
        String fullName = "Jan Maria Rokita";

        String firstname = strategy.getFirstname(fullName);

        assertEquals("Jan", firstname);
    }

    @Test
    void shouldFindMaleName() {
        String names = getNames();

        InputStream inputStream = new ByteArrayInputStream(names.getBytes(StandardCharsets.UTF_8));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean containsName = strategy.checkNameExistence("Michal", bufferedReader);

        assertTrue(containsName);
    }

    @Test
    void shouldFindFemaleName() {
        String names = getNames();

        InputStream inputStream = new ByteArrayInputStream(names.getBytes(StandardCharsets.UTF_8));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean containsName = strategy.checkNameExistence("Anna", bufferedReader);

        assertTrue(containsName);
    }

    private String getNames() {
        return "Michal" +
                System.lineSeparator() +
                "Anna" +
                System.lineSeparator() +
                "Robert";
    }
}