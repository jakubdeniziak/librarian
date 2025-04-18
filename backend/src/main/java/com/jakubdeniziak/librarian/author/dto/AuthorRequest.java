package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
@Builder
public class AuthorRequest {

    @NotBlank(message = "First name is required")
    String firstName;
    @NotBlank(message = "Last name is required")
    String lastName;
    String description;

}
