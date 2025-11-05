package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import com.cobuy.cobuybackend.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

record RegisterRequest(String email, String password, String name) {}
record LoginRequest(String email, String password) {}
record AuthResponse(String token, Integer userId, String email, String name) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTO DE UTILIZADOR
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body("Email já registado!");
        }

        User u = new User();
        u.setEmail(req.email());
        u.setName(req.name());
        u.setPassword(passwordEncoder.encode(req.password())); // <-- ajustado
        userRepository.save(u);

        String token = jwtService.generateToken(Long.valueOf(u.getId()), u.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getName()));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Optional<User> userOpt = userRepository.findByEmail(req.email());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Credenciais inválidas!");
        }

        User u = userOpt.get();
        if (!passwordEncoder.matches(req.password(), u.getPassword())) { // <-- ajustado
            return ResponseEntity.status(401).body("Credenciais inválidas!");
        }

        String token = jwtService.generateToken(Long.valueOf(u.getId()), u.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, u.getId(), u.getEmail(), u.getName()));
    }
}
