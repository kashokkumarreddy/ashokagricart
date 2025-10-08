package com.ashokagricart.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<?> handleEmailNotVerified(EmailNotVerifiedException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("status", "FAILURE");
        response.put("message", ex.getMessage());
        response.put("timestamp", LocalTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
