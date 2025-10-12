package com.jakubdeniziak.librarian.security.dto;

import lombok.Value;

@Value
public class LoginRequest {

    String username;
    String password;

}
