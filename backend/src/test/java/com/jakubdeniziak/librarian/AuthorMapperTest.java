package com.jakubdeniziak.librarian;

import com.jakubdeniziak.librarian.author.Author;
import com.jakubdeniziak.librarian.author.AuthorMapper;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    public void testAuthorsResponseMap() {
        // Given
        UUID id1 = UUID.fromString("a147ef41-01c2-443f-9a33-1092738bdadf");
        String firstName1 = "John";
        String lastName1 = "Doe";

        Author author1 = mock(Author.class);
        when(author1.getId()).thenReturn(id1);
        when(author1.getFirstName()).thenReturn(firstName1);
        when(author1.getLastName()).thenReturn(lastName1);

        UUID id2 = UUID.fromString("33ae2a81-9dd0-4278-a7c0-41d9fd10f98e");
        String firstName2 = "Jack";
        String lastName2 = "Smith";

        Author author2 = mock(Author.class);
        when(author2.getId()).thenReturn(id2);
        when(author2.getFirstName()).thenReturn(firstName2);
        when(author2.getLastName()).thenReturn(lastName2);

        // When
        AuthorsResponse authorsResponse = mapper.map(List.of(author1, author2));

        //Then
        assertEquals(id1, authorsResponse.getAuthors().get(0).getId());
        assertEquals(firstName1, authorsResponse.getAuthors().get(0).getFirstName());
        assertEquals(lastName1, authorsResponse.getAuthors().get(0).getLastName());

        assertEquals(id2, authorsResponse.getAuthors().get(1).getId());
        assertEquals(firstName2, authorsResponse.getAuthors().get(1).getFirstName());
        assertEquals(lastName2, authorsResponse.getAuthors().get(1).getLastName());
    }
}
