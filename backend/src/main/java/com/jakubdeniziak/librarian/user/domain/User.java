package com.jakubdeniziak.librarian.user.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String nickname;

}
