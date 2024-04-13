package com.jakubdeniziak.librarian;

import com.jakubdeniziak.librarian.publisher.Publisher;
import com.jakubdeniziak.librarian.publisher.PublisherMapper;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PublisherMapperTest {
    private PublisherMapper mapper;

    @Mock
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        mapper = new PublisherMapper();
    }

    @Test
    public void testPublisherResponseMap() {
        // Given
        UUID id = UUID.fromString("a147ef41-01c2-443f-9a33-1092738bdadf");
        String name = "Publisher Name";
        String websiteUrl = "https://example.com";
        String description = "Publisher Description";

        when(publisher.getId()).thenReturn(id);
        when(publisher.getName()).thenReturn(name);
        when(publisher.getWebsiteUrl()).thenReturn(websiteUrl);
        when(publisher.getDescription()).thenReturn(description);

        // When
        PublisherResponse response = mapper.map(publisher);

        // Then
        assertEquals(id, response.getId());
        assertEquals(name, response.getName());
        assertEquals(websiteUrl, response.getWebsiteUrl());
        assertEquals(description, response.getDescription());
    }
}
