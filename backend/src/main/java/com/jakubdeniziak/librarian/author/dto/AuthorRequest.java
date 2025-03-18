package com.jakubdeniziak.librarian.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class AuthorRequest {

    @NotBlank(message = "First name is required")
    String firstName;
    @NotBlank(message = "Last name is required")
    String lastName;
    String description;

}
