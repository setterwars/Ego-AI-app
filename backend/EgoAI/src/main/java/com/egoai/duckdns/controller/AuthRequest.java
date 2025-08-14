package com.egoai.duckdns.controller;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
