package com.egoai.duckdns.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

// Model for Auth response
@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
