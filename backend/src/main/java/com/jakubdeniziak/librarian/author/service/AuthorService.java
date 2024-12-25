package com.jakubdeniziak.librarian.author.service;

import com.jakubdeniziak.librarian.author.domain.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    void save(Author author);
    Author find(UUID id);
    List<Author> findAll();
    void update(UUID id, Author updated);
    void delete(UUID id);

}
