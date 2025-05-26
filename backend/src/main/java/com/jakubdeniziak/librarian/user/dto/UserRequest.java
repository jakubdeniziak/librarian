package com.jakubdeniziak.librarian.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserRequest {

    @Size(max = 100)
    String firstName;

    @Size(max = 100)
    String lastName;

    @Size(max = 100)
    String nickname;

}
