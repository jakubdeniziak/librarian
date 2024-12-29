package com.jakubdeniziak.librarian.publisher.controller;

import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;

import java.util.UUID;

public interface PublisherController {

    void create(UUID id, PublisherRequest request);
    PublisherResponse read(UUID id);
    PublishersResponse readAll();
    void update(UUID id, PublisherRequest request);
    void delete(UUID id);

}
