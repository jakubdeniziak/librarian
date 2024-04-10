package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import com.jakubdeniziak.librarian.library.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.library.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.library.librarybook.dto.LibraryBooksResponse;

import java.util.UUID;

public interface LibraryController {
    LibraryResponse readLibrary(UUID id);
    LibrariesResponse readLibraries();
    void createLibrary(UUID id, LibraryRequest request);
    void updateLibrary(UUID id, LibraryRequest request);
    void deleteLibrary(UUID id);

    LibraryBookResponse readBookInLibrary(UUID libraryId, UUID bookId);
    LibraryBooksResponse readAllBooksInLibrary(UUID libraryId);
    void addBookToLibrary(UUID bookId, UUID libraryId, LibraryBookRequest request);

    void deleteBookFromLibrary(UUID bookId, UUID libraryId);
}
