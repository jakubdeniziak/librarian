package com.jakubdeniziak.librarian.librarybook.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class LibraryBookTuple {

    LibraryBook libraryBook;
    UUID libraryId;
    UUID bookId;

}
