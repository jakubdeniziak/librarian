package com.jakubdeniziak.librarian.publisher.service;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;

import java.util.List;
import java.util.UUID;

public interface PublisherService {

    void save(Publisher author);
    Publisher find(UUID id);
    List<Publisher> findAll();
    void update(UUID id, Publisher updated);
    void delete(UUID id);

}
