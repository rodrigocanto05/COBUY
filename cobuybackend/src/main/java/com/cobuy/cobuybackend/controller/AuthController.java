package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public record RegisterRequest(String name, String email, String password, String gender) {}

    public record LoginRequest(String email, String password) {}

    public record AuthResponse(String token) {}

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        String token = authService.register(
                req.name(),
                req.email(),
                req.password(),
                req.gender()   
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        return authService.login(req.email(), req.password())
                .map(t -> ResponseEntity.ok(new AuthResponse(t)))
                .orElse(ResponseEntity.status(401).build());
    }
}