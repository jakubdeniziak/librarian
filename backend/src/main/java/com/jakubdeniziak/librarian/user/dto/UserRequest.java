package com.jakubdeniziak.librarian.user.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class UserRequest {

    UUID id;
    String firstName;
    String lastName;
    String nickname;

}
