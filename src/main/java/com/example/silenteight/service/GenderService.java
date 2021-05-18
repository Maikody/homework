package com.example.silenteight.service;

import com.example.silenteight.algorithm.GuessStrategy;
import com.example.silenteight.algorithm.MajorityRuleStrategy;
import com.example.silenteight.algorithm.OneTokenStrategy;
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
public class GenderService {

    private GuessStrategy guessStrategy;

    public String guessGender(String name, int variant) {
        chooseStrategy(variant);
        return guessStrategy.guessGender(name);
    }

    private void chooseStrategy(int variant) {
        if (variant == 2) {
            guessStrategy = new MajorityRuleStrategy();
        } else {
            guessStrategy = new OneTokenStrategy();
        }
    }

    public List<String> getAvailableTokens() {
        try (JarFile javaFile = new JarFile("tokens.jar")) {
            JarEntry maleFileEntry = javaFile.getJarEntry("male");
            JarEntry femaleFileEntry = javaFile.getJarEntry("female");

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
