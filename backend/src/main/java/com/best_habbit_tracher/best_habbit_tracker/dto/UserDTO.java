package com.best_habbit_tracher.best_habbit_tracker.dto;

public class UserDTO {
    private String email;
    private String passwordHash;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }
}
