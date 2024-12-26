package com.jakubdeniziak.librarian.librarybook.service;

import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;

import java.util.List;
import java.util.UUID;

public interface LibraryBookService {

    void save(LibraryBook libraryBook);
    LibraryBook find(UUID libraryId, UUID bookId);
    List<LibraryBook> findAllByLibrary(UUID libraryId);
    List<LibraryBook> findAll();
    void update(UUID libraryId, UUID bookId, LibraryBook updated);
    void delete(UUID libraryId, UUID bookId);

}
