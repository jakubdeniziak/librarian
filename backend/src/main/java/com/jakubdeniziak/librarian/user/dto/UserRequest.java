package com.jakubdeniziak.librarian.user.dto;

import lombok.Value;

@Value
public class UserRequest {

    String firstName;
    String lastName;
    String nickname;

}
