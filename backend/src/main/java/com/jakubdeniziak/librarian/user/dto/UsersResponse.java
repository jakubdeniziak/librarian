package com.jakubdeniziak.librarian.user.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class UsersResponse {

    @Value
    @Builder
    public static class User {

        UUID id;
        String nickname;

    }

    List<User> users;

}
