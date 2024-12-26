package com.jakubdeniziak.librarian.librarybook.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class LibraryBook {

    private UUID libraryId;
    private UUID bookId;
    private Integer numberOfCopies;

}
