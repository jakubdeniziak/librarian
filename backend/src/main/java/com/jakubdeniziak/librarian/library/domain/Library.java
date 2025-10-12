package com.jakubdeniziak.librarian.library.domain;

import com.jakubdeniziak.librarian.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Library {

    private UUID id;
    private String name;
    private String address;
    private String description;
    private User user;

}
