package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.entity.AuthorEntity;

import java.util.List;

public interface AuthorEntityToDomainMapper {

    Author mapToDomain(AuthorEntity author);
    List<Author> mapToDomain(List<AuthorEntity> authors);

}
