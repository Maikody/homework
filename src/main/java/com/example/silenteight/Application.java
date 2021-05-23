package com.example.silenteight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static final String JARFILE = "tokens.jar";
    public static final String MALENAMESFILE = "male";
    public static final String FEMALENAMESFILE = "female";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
