package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.entity.AuthorEntity;

import java.util.List;

public interface AuthorDomainToEntityMapper {

    AuthorEntity map(Author author);
    List<AuthorEntity> map(List<Author> authors);

}
