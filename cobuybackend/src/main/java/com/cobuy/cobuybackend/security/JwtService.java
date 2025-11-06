package com.cobuy.cobuybackend.security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(Integer userId, String email) {
        return "uid:" + userId + "|email:" + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("uid:") && token.contains("|email:");
    }

    public Integer getUserIdFromToken(String token) {
        try {
            // token: uid:123|email:abc@x.com
            String[] parts = token.split("\\|");
            String uidPart = parts[0]; // "uid:123"
            return Integer.parseInt(uidPart.substring(4));
        } catch (Exception e) {
            return null;
        }
    }
}