package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;

import java.util.List;

public interface AuthorDomainToResponseMapper {

    AuthorResponse mapToResponse(Author author);
    AuthorsResponse mapToResponse(List<Author> authors);

}
