package com.jakubdeniziak.librarian.author.mapper;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorMapper {
    public AuthorResponse map(AuthorEntity authorEntity) {
        return AuthorResponse.builder()
                .id(authorEntity.getId())
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .description(authorEntity.getDescription())
                .build();
    }

    public AuthorsResponse map(List<AuthorEntity> authorEntities) {
        List<AuthorsResponse.Author> authorsResponse = authorEntities.stream()
                .map(author -> new AuthorsResponse.Author(author.getId(), author.getFirstName(), author.getLastName()))
                .toList();
        return new AuthorsResponse(authorsResponse);
    }

    public AuthorEntity map(UUID id, AuthorRequest request) {
        return AuthorEntity.builder()
                .id(id)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .description(request.getDescription())
                .build();
    }
}
