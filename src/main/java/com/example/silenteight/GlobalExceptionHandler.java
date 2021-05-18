package com.example.silenteight;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException() {
        return new ResponseEntity<>("Error during operation on source file", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<?> handleNoSuchFileException() {
        return new ResponseEntity<>("Source file not found", HttpStatus.NOT_FOUND);
    }

}
