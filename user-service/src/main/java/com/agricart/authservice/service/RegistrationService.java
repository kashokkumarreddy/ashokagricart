package com.ashokagricart.authservice.service;

import com.ashokagricart.authservice.dto.RegisterRequestDTO;
import com.ashokagricart.authservice.dto.RegisterResponseDTO;

public interface RegistrationService {
    RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO);
}
