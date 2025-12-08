package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class UserProfileController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserProfileController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(401).body("Não autenticado");
        }

        return ResponseEntity.ok(new Object() {
            public final Integer id = user.getId();
            public final String name = user.getName();
            public final String email = user.getEmail();
            public final String gender = user.getGender();
        });
    }

    public static class UpdateProfileRequest {
        public String name;
        public String gender;
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateProfileRequest req) {
        if (req.name != null && !req.name.isBlank()) {
            user.setName(req.name.trim());
        }

        if (req.gender != null && !req.gender.isBlank()) {
            user.setGender(req.gender.trim());
        }

        userRepo.save(user);
        return ResponseEntity.ok("Perfil atualizado");
    }

    public static class UpdateEmailRequest {
        public String email;
    }

    @PutMapping("/email")
    public ResponseEntity<?> updateEmail(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateEmailRequest req) {
        if (req.email == null || req.email.isBlank()) {
            return ResponseEntity.badRequest().body("Email é obrigatório");
        }

        if (userRepo.existsByEmail(req.email) && !req.email.equals(user.getEmail())) {
            return ResponseEntity.status(409).body("Email já está em uso");
        }

        user.setEmail(req.email.trim());
        userRepo.save(user);

        return ResponseEntity.ok("Email atualizado");
    }

    public static class UpdatePasswordRequest {
        public String oldPassword;
        public String newPassword;
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(
            @AuthenticationPrincipal User user,
            @RequestBody UpdatePasswordRequest req) {
        if (req.oldPassword == null || req.newPassword == null) {
            return ResponseEntity.badRequest().body("oldPassword e newPassword obrigatórios");
        }

        if (!passwordEncoder.matches(req.oldPassword, user.getPassword())) {
            return ResponseEntity.status(403).body("Password antiga incorreta");
        }

        user.setPassword(passwordEncoder.encode(req.newPassword));
        userRepo.save(user);

        return ResponseEntity.ok("Password atualizada");
    }
}
