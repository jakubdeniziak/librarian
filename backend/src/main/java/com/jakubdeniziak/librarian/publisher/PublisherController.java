package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;

import java.util.UUID;

public interface PublisherController {
    PublisherResponse readPublisher(UUID id);

    PublishersResponse readPublishers();

    void createPublisher(UUID id, PublisherRequest request);

    void updatePublisher(UUID id, PublisherRequest request);

    void deletePublisher(UUID id);
}
