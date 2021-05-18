package com.example.silenteight.web;

import com.example.silenteight.service.GenderDetectorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/gender")
public class GenderDetectorController {

    private final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping("/{name}/{variant}")
    public String detectGenderByName(@PathVariable String name, @PathVariable int variant) {
        return service.detectGenderByName(name, variant);
    }

    @GetMapping("/tokens")
    public List<String> getAllNameTokens() {
        return service.getAvailableNameTokens();
    }
}
