package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.book.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {

    void save(Book author);
    Optional<Book> find(UUID id);
    List<Book> findAllByAuthor(UUID authorId);
    List<Book> findAllByPublisher(UUID publisherId);
    List<Book> findAll();
    void update(UUID id, Book updated);
    void delete(UUID id);

}
