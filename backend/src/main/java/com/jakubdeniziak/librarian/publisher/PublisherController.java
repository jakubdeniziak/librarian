package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;

import java.util.UUID;

public interface PublisherController {
    PublisherResponse readPublisher(UUID id);

    PublishersResponse readPublishers();
    BooksResponse readBooksByPublisher(UUID publisherId);

    void createPublisher(UUID id, PublisherRequest request);

    void updatePublisher(UUID id, PublisherRequest request);

    void deletePublisher(UUID id);
}
