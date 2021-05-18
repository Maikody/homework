package com.example.silenteight.service;

import com.example.silenteight.algorithm.GenderDetectingStrategy;
import com.example.silenteight.algorithm.MajorityRuleStrategy;
import com.example.silenteight.algorithm.SingleTokenNameStrategy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

@Service
public class GenderDetectorService {

    private static final String JARFILE = "tokens.jar";
    private static final String MALENAMESFILE = "male";
    private static final String FEMALENAMESFILE = "female";
    private GenderDetectingStrategy detectingStrategy;

    public String detectGenderByName(String name, int variant) {
        setDetectingStrategy(variant);
        return detectingStrategy.detectGenderByName(name);
    }

    private void setDetectingStrategy(int variant) {
        if (variant == 2) {
            detectingStrategy = new MajorityRuleStrategy();
        } else {
            detectingStrategy = new SingleTokenNameStrategy();
        }
    }

    public List<String> getAvailableNameTokens() {
        try (JarFile javaFile = new JarFile(JARFILE)) {
            JarEntry maleFileEntry = javaFile.getJarEntry(MALENAMESFILE);
            JarEntry femaleFileEntry = javaFile.getJarEntry(FEMALENAMESFILE);

            InputStream inputMale = javaFile.getInputStream(maleFileEntry);
            InputStream inputFemale = javaFile.getInputStream(femaleFileEntry);

            try (BufferedReader bufferedReaderMale = new BufferedReader(new InputStreamReader(inputMale));
                 BufferedReader bufferedReaderFemale = new BufferedReader(new InputStreamReader(inputFemale))) {

                List<String> maleNames = bufferedReaderMale.lines().collect(Collectors.toList());
                List<String> femaleNames = bufferedReaderFemale.lines().collect(Collectors.toList());

                List<String> names = new ArrayList<>();
                names.addAll(maleNames);
                names.addAll(femaleNames);

                return names;
            } catch (IOException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
