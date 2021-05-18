package com.example.silenteight.algorithm;

public abstract class GenderDetectingStrategy {
    protected static final String MALE = "MALE";
    protected static final String FEMALE = "FEMALE";
    protected static final String INCONCLUSIVE = "INCONCLUSIVE";
    protected static final String JARFILE = "tokens.jar";
    protected static final String MALENAMESFILE = "male";
    protected static final String FEMALENAMESFILE = "female";

    public abstract String detectGenderByName(String name);
}
