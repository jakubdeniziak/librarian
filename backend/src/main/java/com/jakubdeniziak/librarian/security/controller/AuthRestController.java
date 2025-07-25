package com.jakubdeniziak.librarian.security.controller;

import com.jakubdeniziak.librarian.security.dto.LoginRequest;
import com.jakubdeniziak.librarian.security.dto.LoginResponse;
import com.jakubdeniziak.librarian.security.service.DefaultUserDetailsService;
import com.jakubdeniziak.librarian.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AuthRestController implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final DefaultUserDetailsService userDetailsService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user.getUsername(), user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet()));
        return ResponseEntity.ok(new LoginResponse(token));
    }

}
