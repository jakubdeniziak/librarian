package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;

import java.util.UUID;

public interface AuthorController {
    AuthorResponse readAuthor(UUID id);

    AuthorsResponse readAuthors();

    void createAuthor(UUID id, AuthorRequest request);

    void updateAuthor(UUID id, AuthorRequest request);

    void deleteAuthor(UUID id);
}
