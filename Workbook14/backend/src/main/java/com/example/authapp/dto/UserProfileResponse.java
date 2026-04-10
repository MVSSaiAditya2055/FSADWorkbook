package com.example.authapp.dto;

public class UserProfileResponse {
    private final Long id;
    private final String username;
    private final String fullName;
    private final String email;

    public UserProfileResponse(Long id, String username, String fullName, String email) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
