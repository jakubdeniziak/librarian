package com.jakubdeniziak.librarian;

import com.jakubdeniziak.librarian.publisher.Publisher;
import com.jakubdeniziak.librarian.publisher.PublisherMapper;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
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

    @Test
    public void testPublishersResponse() {
        // Given
        UUID id1 = UUID.fromString("a147ef41-01c2-443f-9a33-1092738bdadf");
        UUID id2 = UUID.fromString("33ae2a81-9dd0-4278-a7c0-41d9fd10f98e");
        String name1 = "Publisher 1";
        String name2 = "Publisher 2";

        List<Publisher> publishers = List.of(
                Publisher.builder().id(id1).name(name1).build(),
                Publisher.builder().id(id2).name(name2).build()
        );

        // When
        PublishersResponse response = mapper.map(publishers);

        // Then
        assertEquals(publishers.size(), response.getPublishers().size());
        assertEquals(id1, response.getPublishers().get(0).getId());
        assertEquals(name1, response.getPublishers().get(0).getName());
        assertEquals(id2, response.getPublishers().get(1).getId());
        assertEquals(name2, response.getPublishers().get(1).getName());
    }
}
