package com.jakubdeniziak.librarian.author.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Author {

    private UUID id;
    private String firstName;
    private String lastName;
    private String description;

}
