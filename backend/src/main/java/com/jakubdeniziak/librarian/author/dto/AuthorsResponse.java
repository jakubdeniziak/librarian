package com.jakubdeniziak.librarian.author.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class AuthorsResponse {

    @Value
    public static class Author {

        UUID id;
        String firstName;
        String lastName;

    }

    List<Author> authors;

}
