package com.example.silenteight.web;

import com.example.silenteight.service.GenderDetectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1/gender")
public class GenderDetectorController {

    private final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping("/{name}/{variant}")
    public ResponseEntity<String> detectGenderByName(@PathVariable String name, @PathVariable int variant) throws IOException {
        return new ResponseEntity<>(service.detectGenderByName(name, variant), HttpStatus.OK);
    }

    @GetMapping("/tokens")
    public ResponseEntity<List<String>> getAllNameTokens() throws IOException {
        return new ResponseEntity<>(service.getAvailableNameTokensStoredInMemo(), HttpStatus.OK);
    }

    @GetMapping("/tokenstream")
    public ResponseEntity<Stream<String>> getAllNameTokensStream() throws IOException {
        return new ResponseEntity<>(service.getAvailableNameTokensStream(), HttpStatus.OK);
    }

}
