package com.example.silenteight.service;

import com.example.silenteight.algorithm.GenderDetectingStrategy;
import com.example.silenteight.algorithm.MajorityRuleStrategy;
import com.example.silenteight.algorithm.SingleTokenNameStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenderDetectorServiceTest {

    @Autowired
    private GenderDetectorService service;

    @Test
    void shouldSetSingleTokenDetectingStrategy() {
        int variant = 1;

        GenderDetectingStrategy detectingStrategy = service.setDetectingStrategy(variant);

        assertTrue(detectingStrategy instanceof SingleTokenNameStrategy);
    }

    @Test
    void shouldSetMajorityRuleDetectingStrategy() {
        int variant = 2;

        GenderDetectingStrategy detectingStrategy = service.setDetectingStrategy(variant);

        assertTrue(detectingStrategy instanceof MajorityRuleStrategy);
    }
}