package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorMapper {
    public AuthorResponse map(Author author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .build();
    }

    public AuthorsResponse map(List<Author> authors) {
        return AuthorsResponse.builder()
                .authors(authors.stream()
                        .map(author -> AuthorsResponse.Author.builder()
                                .id(author.getId())
                                .firstName(author.getFirstName())
                                .lastName(author.getLastName())
                                .build())
                        .toList())
                .build();
    }

    public Author map(UUID id, AuthorRequest request) {
        return Author.builder()
                .id(id)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .description(request.getDescription())
                .build();
    }
}
