package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import com.jakubdeniziak.librarian.author.entity.AuthorEntity;

import java.util.List;
import java.util.UUID;

public interface AuthorMapper {

    Author map(UUID id, AuthorRequest request);
    AuthorEntity map(Author author);
    Author map(AuthorEntity author);
    List<Author> map(List<AuthorEntity> authors);
    AuthorResponse mapToResponse(Author author);
    AuthorsResponse mapToResponse(List<Author> authors);

}
