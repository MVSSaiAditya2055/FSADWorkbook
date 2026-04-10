package com.example.authapp.dto;

public class LoginResponse {
    private final Long userId;
    private final String username;

    public LoginResponse(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
