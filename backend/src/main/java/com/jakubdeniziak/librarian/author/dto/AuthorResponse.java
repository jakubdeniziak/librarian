package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AuthorResponse {

    UUID id;
    String firstName;
    String lastName;
    String description;

}
