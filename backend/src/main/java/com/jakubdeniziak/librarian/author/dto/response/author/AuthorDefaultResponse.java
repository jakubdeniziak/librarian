package com.jakubdeniziak.librarian.author.dto.response.author;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AuthorDefaultResponse implements AuthorResponse {

    UUID id;
    String firstName;
    String lastName;
    String description;

}

