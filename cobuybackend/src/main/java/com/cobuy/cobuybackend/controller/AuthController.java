// src/main/java/com/cobuy/cobuybackend/controller/AuthController.java
package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import com.cobuy.cobuybackend.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final JwtService jwtService;

  public AuthController(UserRepository userRepository, JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  record RegisterRequest(String name, String email, String password) {}
  record LoginRequest(String email, String password) {}
  record AuthResponse(String token) {}

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
    if (userRepository.existsByEmail(req.email())) {
      return ResponseEntity.status(409).build(); // já existe
    }
    User u = new User();
    u.setName(req.name());
    u.setEmail(req.email());
    u.setPassword(req.password()); // em dev, simples (ideal: BCrypt)
    u.setCreatedAt(LocalDateTime.now());
    u = userRepository.save(u);

    String token = jwtService.generateToken(u.getId(), u.getEmail());
    return ResponseEntity.ok(new AuthResponse(token));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
    return userRepository.findByEmail(req.email())
      .filter(u -> u.getPassword().equals(req.password()))
      .map(u -> ResponseEntity.ok(new AuthResponse(jwtService.generateToken(u.getId(), u.getEmail()))))
      .orElseGet(() -> ResponseEntity.status(401).build());
  }
}