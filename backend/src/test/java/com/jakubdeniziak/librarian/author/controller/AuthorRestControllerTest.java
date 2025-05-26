package com.jakubdeniziak.librarian.author.controller;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorDefaultResponse;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.author.mapper.AuthorDomainToResponseMapper;
import com.jakubdeniziak.librarian.author.mapper.AuthorRequestToDomainMapper;
import com.jakubdeniziak.librarian.author.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorRestControllerTest {

    private static final UUID ID = UUID.fromString("8c6ed771-5d86-4e08-b3dd-03c21af5cb56");
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final String DESCRIPTION = "A bestselling author.";
    private static final AuthorRequest CREATE_REQUEST = AuthorRequest.builder()
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .description(DESCRIPTION)
            .build();
    private static final Author AUTHOR = Author.builder()
            .id(ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .description(DESCRIPTION)
            .build();
    private static final AuthorDefaultResponse READ_RESPONSE = AuthorDefaultResponse.builder()
            .id(ID)
            .firstName(FIRST_NAME)
            .lastName(LAST_NAME)
            .description(DESCRIPTION)
            .build();

    @Mock
    private AuthorService authorService;
    @Mock
    private AuthorRequestToDomainMapper authorRequestToDomainMapper;
    @Mock
    private AuthorDomainToResponseMapper authorDomainToResponseMapper;
    @InjectMocks
    private AuthorRestController authorController;

    @Test
    public void shouldCreateAuthor() {
        when(authorRequestToDomainMapper.map(ID, CREATE_REQUEST)).thenReturn(AUTHOR);

        authorController.create(ID, CREATE_REQUEST);

        verify(authorRequestToDomainMapper).map(ID, CREATE_REQUEST);
        verify(authorService).save(AUTHOR);
    }

    @Test
    public void shouldReadAuthor() {
        when(authorService.find(ID)).thenReturn(AUTHOR);
        when(authorDomainToResponseMapper.mapToResponse(AUTHOR)).thenReturn(READ_RESPONSE);

        AuthorResponse response = authorController.read(ID);

        assertThat(response)
                .returns(ID, AuthorResponse::getId)
                .returns(FIRST_NAME, AuthorResponse::getFirstName)
                .returns(LAST_NAME, AuthorResponse::getLastName)
                .returns(DESCRIPTION, AuthorResponse::getDescription);

        verify(authorService).find(ID);
        verify(authorDomainToResponseMapper).mapToResponse(AUTHOR);
    }

    @Test
    public void shouldDeleteAuthor() {
        authorController.delete(ID);

        verify(authorService).delete(ID);
    }

}
