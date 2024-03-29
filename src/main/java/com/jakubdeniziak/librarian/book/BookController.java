package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;

import java.util.UUID;

public interface BookController {
    BookResponse readBook(UUID id);

    BooksResponse readBooks();

    void createBook(UUID id, BookRequest request);

    void updateBook(UUID id, BookRequest request);

    void deleteBook(UUID id);
}
