package com.jakubdeniziak.librarian.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
public class DefaultJwtService implements JwtService {

    private final SecretKey key = Keys.hmacShaKeyFor(
            Base64.getDecoder().decode(System.getenv("JWT_SECRET"))
    );

    @Override
    public String generateToken(String username, Set<String> roles) {
        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.toInstant().toEpochMilli() + 1000);
        return Jwts.builder()
                .subject(username)
                .issuedAt(issueDate)
                .expiration(expirationDate)
                .id(UUID.randomUUID().toString())
                .signWith(key)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                    .verifyWith(key)
                    .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token, String expectedUsername) {
        try {
            Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                    .parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            return username.equals(expectedUsername) && !isTokenExpired(claims);
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration() != null && claims.getExpiration().before(new Date());
    }

}
