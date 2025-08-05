package com.jakubdeniziak.librarian.security.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegisterRequest {

    String username;
    String password;

}
