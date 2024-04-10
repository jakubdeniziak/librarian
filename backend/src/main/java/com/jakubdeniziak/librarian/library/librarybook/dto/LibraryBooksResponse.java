package com.jakubdeniziak.librarian.library.librarybook.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class LibraryBooksResponse {
    private List<LibraryBook> libraryBooks;

    @Builder
    @Getter
    public static class LibraryBook {
        private Long id;
        private UUID libraryId;
        private UUID bookId;
        private int numberOfCopies;
    }
}
