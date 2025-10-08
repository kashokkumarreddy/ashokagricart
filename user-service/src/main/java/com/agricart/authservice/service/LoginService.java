package com.ashokagricart.authservice.service;

import com.ashokagricart.authservice.dto.LoginRequestDTO;
import com.ashokagricart.authservice.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
