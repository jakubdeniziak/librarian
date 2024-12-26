package com.jakubdeniziak.librarian.librarybook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class LibraryBooksResponse {

    @Value
    @Builder
    public static class LibraryBook {

        UUID libraryId;
        UUID bookId;
        Integer numberOfCopies;

    }

    List<LibraryBook> libraryBooks;

}
