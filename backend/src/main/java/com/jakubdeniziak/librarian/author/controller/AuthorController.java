package com.jakubdeniziak.librarian.author.controller;

import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;

import java.util.UUID;

public interface AuthorController {

    void create(UUID id, AuthorRequest request);
    AuthorResponse read(UUID id);
    AuthorsResponse readAll();
    void update(UUID id, AuthorRequest request);
    void delete(UUID id);

}
