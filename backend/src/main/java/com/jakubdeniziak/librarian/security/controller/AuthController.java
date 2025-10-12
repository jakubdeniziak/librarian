package com.jakubdeniziak.librarian.security.controller;

import com.jakubdeniziak.librarian.security.dto.LoginRequest;
import com.jakubdeniziak.librarian.security.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> login(LoginRequest request);

}
