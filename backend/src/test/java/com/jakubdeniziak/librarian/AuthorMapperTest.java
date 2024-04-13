package com.jakubdeniziak.librarian;

import com.jakubdeniziak.librarian.author.Author;
import com.jakubdeniziak.librarian.author.AuthorMapper;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorMapperTest {
    private AuthorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new AuthorMapper();
    }

    @Test
    public void testAuthorResponseMap() {
        UUID id = UUID.fromString("a147ef41-01c2-443f-9a33-1092738bdadf");
        String firstName = "John";
        String lastName = "Doe";
        String description = "A great author";

        Author author = mock(Author.class);
        when(author.getId()).thenReturn(id);
        when(author.getFirstName()).thenReturn(firstName);
        when(author.getLastName()).thenReturn(lastName);
        when(author.getDescription()).thenReturn(description);

        AuthorResponse response = mapper.map(author);

        assertEquals(id, response.getId());
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(description, response.getDescription());
    }
}
