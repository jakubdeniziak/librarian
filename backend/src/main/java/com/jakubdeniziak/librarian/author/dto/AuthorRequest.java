package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorRequest {

    String firstName;
    String lastName;
    String description;

}
