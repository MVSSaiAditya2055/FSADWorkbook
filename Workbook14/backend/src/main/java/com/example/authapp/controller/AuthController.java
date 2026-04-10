package com.example.authapp.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapp.dto.LoginRequest;
import com.example.authapp.dto.LoginResponse;
import com.example.authapp.dto.RegisterRequest;
import com.example.authapp.dto.UserProfileResponse;
import com.example.authapp.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/users/profile")
    public ResponseEntity<UserProfileResponse> profile(
        @RequestParam Optional<Long> userId,
        @RequestParam Optional<String> username
    ) {
        return ResponseEntity.ok(authService.getProfile(userId, username));
    }
}
