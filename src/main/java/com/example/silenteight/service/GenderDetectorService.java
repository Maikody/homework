package com.example.silenteight.service;

import com.example.silenteight.algorithm.GenderDetectingStrategy;
import com.example.silenteight.algorithm.MajorityRuleStrategy;
import com.example.silenteight.algorithm.SingleTokenNameStrategy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenderDetectorService {

    private static final String JARFILE = "tokens.jar";
    private static final String MALENAMESFILE = "male";
    private static final String FEMALENAMESFILE = "female";

    public String detectGenderByName(String name, int variant) throws IOException {
        GenderDetectingStrategy detectingStrategy = setDetectingStrategy(variant);
        return detectingStrategy.detectGenderByName(name);
    }

    public GenderDetectingStrategy setDetectingStrategy(int variant) {
        if (variant == 2) {
            return new MajorityRuleStrategy();
        } else {
            return new SingleTokenNameStrategy();
        }
    }

    public List<String> getAvailableNameTokensStoredInMemo() throws IOException {
        try (JarFile javaFile = new JarFile(JARFILE)) {
            JarEntry maleFileEntry = javaFile.getJarEntry(MALENAMESFILE);
            JarEntry femaleFileEntry = javaFile.getJarEntry(FEMALENAMESFILE);

            InputStream inputMale = javaFile.getInputStream(maleFileEntry);
            InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                return Stream.concat(bufferedReaderFemale.lines(), bufferedReaderMale.lines()).collect(Collectors.toList());
            }
        }
    }

    /*Data is not saved in memory however, resources are not closed*/
    public Stream<String> getAvailableNameTokensStream() throws IOException {
        JarFile javaFile = new JarFile(JARFILE);
        JarEntry maleFileEntry = javaFile.getJarEntry(MALENAMESFILE);
        JarEntry femaleFileEntry = javaFile.getJarEntry(FEMALENAMESFILE);

        InputStream inputMale = javaFile.getInputStream(maleFileEntry);
        InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

        BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
        BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale));

        return Stream.concat(bufferedReaderFemale.lines(), bufferedReaderMale.lines());
    }
}
