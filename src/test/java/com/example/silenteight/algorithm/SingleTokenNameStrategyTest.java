package com.example.silenteight.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleTokenNameStrategyTest {
    private final SingleTokenNameStrategy strategy = new SingleTokenNameStrategy();

    @Test
    void shouldGetFirstName() {
        String fullName = "Jan Maria Rokita";

        String firstname = strategy.getFirstname(fullName);

        assertEquals("Jan", firstname);
    }

    @Test
    void shouldFindMale(@TempDir Path tempDir) throws IOException {

    }
}