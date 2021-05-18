package com.example.silenteight.web;

import com.example.silenteight.service.GenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/names")
public class NameController {

    private final GenderService service;

    public NameController(GenderService service) {
        this.service = service;
    }

    @GetMapping("/gender/{name}/{variant}")
    public String guessGender(@PathVariable String name, @PathVariable int variant) {
        return service.guessGender(name, variant);
    }

    @GetMapping("/tokens")
    public List<String> getNames() {
        return service.getAvailableTokens();
    }
}
