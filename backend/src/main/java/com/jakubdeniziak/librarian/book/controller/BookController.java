package com.jakubdeniziak.librarian.book.controller;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.book.dto.response.books.BooksResponse;

import java.util.UUID;

public interface BookController {

    void create(UUID id, BookRequest request);
    BookResponse read(UUID id);
    BooksResponse readAllByAuthor(UUID authorId);
    BooksResponse readAllByPublisher(UUID publisherId);
    BooksResponse readAll();
    void update(UUID id, BookRequest request);
    void delete(UUID id);

}
