package com.jakubdeniziak.librarian.librarybook.controller;

import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;

import java.util.UUID;

public interface LibraryBookController {

    void create(UUID libraryId, UUID bookId, LibraryBookRequest request);
    LibraryBookResponse read(UUID libraryId, UUID bookId);
    LibraryBooksResponse readAllByLibrary(UUID libraryId);
    LibraryBooksResponse readAll();
    void update(UUID libraryId, UUID bookId, LibraryBookRequest request);
    void delete(UUID libraryId, UUID bookId);

}
