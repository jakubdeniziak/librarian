package com.jakubdeniziak.librarian.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class LibraryRequest {

    @NotBlank
    @Size(max = 255)
    String name;

    @NotBlank
    @Size(max = 255)
    String address;

    @Size(max = 1000)
    String description;

}
