package com.jakubdeniziak.librarian.author.dto.standard;

import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AuthorStandardResponse implements AuthorResponse {

    UUID id;
    String firstName;
    String lastName;
    String description;

}

