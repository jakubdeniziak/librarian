package com.jakubdeniziak.librarian.librarybook;

import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;

import java.util.UUID;

public interface LibraryBookController {

    LibraryBookResponse readBookInLibrary(UUID libraryId, UUID bookId);
    LibraryBooksResponse readAllBooksInLibrary(UUID libraryId);
    void addBookToLibrary(UUID bookId, UUID libraryId, LibraryBookRequest request);
    void deleteBookFromLibrary(UUID bookId, UUID libraryId);

}
