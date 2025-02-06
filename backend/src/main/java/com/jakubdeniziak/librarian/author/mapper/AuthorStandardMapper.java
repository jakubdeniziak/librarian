package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.standard.AuthorStandardResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Primary
public class AuthorStandardMapper implements AuthorRequestToDomainMapper, AuthorDomainToEntityMapper,
        AuthorEntityToDomainMapper, AuthorDomainToResponseMapper {

    @Override
    public Author map(UUID id, AuthorRequest request) {
        return Author.builder()
                .id(id)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .description(request.getDescription())
                .build();
    }

    @Override
    public AuthorEntity map(Author author) {
        return AuthorEntity.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .build();
    }

    @Override
    public List<AuthorEntity> map(List<Author> authors) {
        return authors.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public Author mapToDomain(AuthorEntity author) {
        return Author.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .build();
    }

    @Override
    public List<Author> mapToDomain(List<AuthorEntity> authors) {
        return authors.stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public AuthorResponse mapToResponse(Author author) {
        return AuthorStandardResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .build();
    }

    @Override
    public AuthorsResponse mapToResponse(List<Author> authors) {
        List<AuthorsResponse.Author> responseAuthors = authors.stream()
                .map(author -> AuthorsResponse.Author.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .build())
                .toList();
        return AuthorsResponse.builder()
                .authors(responseAuthors)
                .build();
    }

}
