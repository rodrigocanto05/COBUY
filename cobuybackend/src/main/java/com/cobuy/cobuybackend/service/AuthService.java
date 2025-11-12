package com.cobuy.cobuybackend.service;

import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.UserRepository;
import com.cobuy.cobuybackend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public String register(String name, String email, String rawPassword) {
    if (userRepository.existsByEmail(email)) {
      throw new IllegalStateException("Email já existe");
    }
    User u = new User();
    u.setName(name);
    u.setEmail(email);
    u.setPassword(passwordEncoder.encode(rawPassword)); // <<< HASH aqui
    u.setCreatedAt(LocalDateTime.now());
    u = userRepository.save(u);
    return jwtService.generateToken(u.getId(), u.getEmail());
  }

  public Optional<String> login(String email, String rawPassword) {
    return userRepository.findByEmail(email).flatMap(u -> {
      boolean ok = passwordEncoder.matches(rawPassword, u.getPassword())
          || rawPassword.equals(u.getPassword()); 

      if (!ok)
        return Optional.empty();

      if (rawPassword.equals(u.getPassword())) {
        u.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(u);
      }
      return Optional.of(jwtService.generateToken(u.getId(), u.getEmail()));
    });
  }
}