package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;

import java.util.UUID;

public interface AuthorRequestToDomainMapper {

    Author map(UUID id, AuthorRequest request);

}
