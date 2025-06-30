package com.ashokagricart.authservice.controller;

import com.ashokagricart.authservice.dto.RegisterRequestDTO;
import com.ashokagricart.authservice.dto.RegisterResponseDTO;
import com.ashokagricart.authservice.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @RequestBody @Valid RegisterRequestDTO registerRequestDTO){
        RegisterResponseDTO response  = registrationService.registerUser(registerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
