package com.cobuy.cobuybackend.service;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public User register(String name, String email, String password) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(password));
        u.setCreatedAt(LocalDateTime.now());
        return userRepository.save(u);
    }
}

