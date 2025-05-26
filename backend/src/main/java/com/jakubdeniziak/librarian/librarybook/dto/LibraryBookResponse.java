package com.jakubdeniziak.librarian.librarybook.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class LibraryBookResponse {

    UUID libraryId;
    UUID bookId;
    Integer numberOfCopies;

}
