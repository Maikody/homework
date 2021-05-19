package com.example.silenteight.web;

import com.example.silenteight.domain.Gender;
import com.example.silenteight.service.GenderDetectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenderDetectorController.class)
class GenderDetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenderDetectorService service;

    @Test
    void shouldDetectGenderByName() throws Exception {
        String name = "Robert";
        int variant = 1;

        when(service.detectGenderByName(name, variant)).thenReturn(Gender.MALE);

        mockMvc.perform(get("/v1/gender/{name}/{variant}", name, variant))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldProvideAllNameTokensStoredInMemo() throws Exception {
        when(service.getAvailableNameTokensStoredInMemo()).thenReturn(List.of("Anna", "Klaudia", "Marek", "Andrzej"));

        mockMvc.perform(get("/v1/gender/tokens"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void shouldProvideAllNameTokensStored() throws Exception {
        when(service.getAvailableNameTokensStream()).thenReturn(Stream.of("Anna", "Klaudia", "Marek", "Andrzej"));

        mockMvc.perform(get("/v1/gender/tokenstream"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }
}