package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author map(UUID id, AuthorRequest request);

    AuthorEntity mapToEntity(Author author);

    Author map(AuthorEntity entity);

    Optional<Author> map(Optional<AuthorEntity> entity);

    List<Author> map(List<AuthorEntity> authors);

    AuthorResponse mapToResponse(Author author);

    AuthorsResponse mapToResponse(List<Author> authors);

}
