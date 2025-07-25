package com.jakubdeniziak.librarian.security.controller;

import com.jakubdeniziak.librarian.security.dto.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<?> login(LoginRequest request);

}
