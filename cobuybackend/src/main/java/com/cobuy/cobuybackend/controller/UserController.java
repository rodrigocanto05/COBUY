package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) { this.userRepository = userRepository; }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            User saved = userRepository.save(user);
            return ResponseEntity.created(URI.create("/users/" + saved.getId())).body(saved);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Email já existe.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User in) {
        return userRepository.findById(id).map(u -> {
            u.setName(in.getName());
            u.setEmail(in.getEmail());
            if (in.getPassword() != null && !in.getPassword().isBlank()) {
                u.setPassword(in.getPassword());
            }
            return ResponseEntity.ok(userRepository.save(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!userRepository.existsById(id)) return ResponseEntity.notFound().build();
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}