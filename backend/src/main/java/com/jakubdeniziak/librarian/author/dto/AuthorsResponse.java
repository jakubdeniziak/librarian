package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class AuthorsResponse {

    @Value
    @Builder
    public static class Author {

        UUID id;
        String firstName;
        String lastName;

    }

    List<Author> authors;

}
