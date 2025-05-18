package com.jakubdeniziak.librarian.book.dto;

import com.jakubdeniziak.librarian.book.entity.BookFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.UUID;

@Value
public class BookRequest {

    @NotBlank(message = "ISBN is required")
    @Pattern(
            regexp = "^(\\d{10}|\\d{13})$",
            message = "ISBN must be either 10 or 13 digits"
    )
    String isbn;

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    String description;

    @NotNull(message = "Book format is required")
    BookFormat format;

    @NotNull(message = "Author ID is required")
    UUID authorId;

    @NotNull(message = "Publisher ID is required")
    UUID publisherId;

}
