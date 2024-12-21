package com.jakubdeniziak.librarian.librarybook;

import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryBookMapper {
    public LibraryBookResponse map(LibraryBook libraryBook) {
        return LibraryBookResponse.builder()
                .id(libraryBook.getId())
                .bookId(libraryBook.getBookEntity().getId())
                .libraryId(libraryBook.getBookEntity().getId())
                .numberOfCopies(libraryBook.getNumberOfCopies())
                .build();
    }

    public LibraryBooksResponse map(List<LibraryBook> books) {
        return LibraryBooksResponse.builder()
                .libraryBooks(books.stream()
                        .map(libraryBook -> LibraryBooksResponse.LibraryBook.builder()
                                .id(libraryBook.getId())
                                .bookId(libraryBook.getBookEntity().getId())
                                .libraryId(libraryBook.getLibraryEntity().getId())
                                .numberOfCopies(libraryBook.getNumberOfCopies())
                                .build())
                        .toList())
                .build();
    }
}
