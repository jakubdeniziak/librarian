package com.jakubdeniziak.librarian.library.librarybook.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class LibraryBookResponse {
    private Long id;
    private UUID libraryId;
    private UUID bookId;
    private int numberOfCopies;
}
