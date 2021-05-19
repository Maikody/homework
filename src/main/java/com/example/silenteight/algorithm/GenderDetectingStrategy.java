package com.example.silenteight.algorithm;

import com.example.silenteight.domain.Gender;

import java.io.IOException;

public abstract class GenderDetectingStrategy {
    protected static final String JARFILE = "tokens.jar";
    protected static final String MALENAMESFILE = "male";
    protected static final String FEMALENAMESFILE = "female";

    public abstract Gender detectGenderByName(String name) throws IOException;
}
