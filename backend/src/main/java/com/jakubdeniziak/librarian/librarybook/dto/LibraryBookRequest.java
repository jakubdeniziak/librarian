package com.jakubdeniziak.librarian.librarybook.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;

@Value
public class LibraryBookRequest {

    @PositiveOrZero
    Integer numberOfCopies;

}
