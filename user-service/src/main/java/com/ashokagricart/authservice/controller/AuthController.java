package com.ashokagricart.authservice.controller;

import com.ashokagricart.authservice.dto.LoginRequestDTO;
import com.ashokagricart.authservice.dto.LoginResponseDTO;
import com.ashokagricart.authservice.dto.RegisterRequestDTO;
import com.ashokagricart.authservice.dto.RegisterResponseDTO;
import com.ashokagricart.authservice.service.LoginService;
import com.ashokagricart.authservice.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @RequestBody @Valid RegisterRequestDTO registerRequestDTO){
        RegisterResponseDTO response  = registrationService.registerUser(registerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginRequestDTO
            ){
        LoginResponseDTO response = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }

}
