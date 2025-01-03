package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.domain.BookTuple;

import java.util.List;
import java.util.UUID;

public interface BookService {

    void save(Book book, UUID authorId, UUID publisherId);
    void saveAll(List<BookTuple> bookTuples);
    Book find(UUID id);
    List<Book> findAllByAuthor(UUID authorId);
    List<Book> findAllByPublisher(UUID publisherId);
    List<Book> findAll();
    void update(UUID id, Book updated, UUID authorId, UUID publisherId);
    void delete(UUID id);

}
