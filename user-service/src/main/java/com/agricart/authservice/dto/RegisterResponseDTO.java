package com.ashokagricart.authservice.dto;

import lombok.Data;

@Data
public class RegisterResponseDTO {
    private long userId;
    private String email;
    private String status;
    private String message;
}
