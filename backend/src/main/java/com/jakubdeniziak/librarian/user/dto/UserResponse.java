package com.jakubdeniziak.librarian.user.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserResponse {

    UUID id;
    String firstName;
    String lastName;
    String nickname;

}
