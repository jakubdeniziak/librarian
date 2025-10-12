package com.jakubdeniziak.librarian.security.service;

import java.util.Set;

public interface JwtService {

    String generateToken(String username, Set<String> roles);
    String extractUsername(String token);
    boolean isTokenValid(String token, String expectedUsername);

}
