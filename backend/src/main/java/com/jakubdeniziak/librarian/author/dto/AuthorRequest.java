package com.jakubdeniziak.librarian.author.dto;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class AuthorRequest {

    @NotBlank(message = "First name is required")
    @Length(max = 100, message = "Maximum length for first name is 100 characters")
    String firstName;
    @NotBlank(message = "Last name is required")
    @Length(max = 100, message = "Maximum length for last name is 100 characters")
    String lastName;
    String description;

}
