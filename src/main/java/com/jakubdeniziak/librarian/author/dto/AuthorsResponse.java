package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class AuthorsResponse {
    private List<Author> authors;

    @Builder
    @Getter
    public static class Author {
        private UUID id;
        private String firstName;
        private String lastName;
    }
}
