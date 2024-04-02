package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class AuthorResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
}
