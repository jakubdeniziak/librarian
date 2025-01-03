package com.jakubdeniziak.librarian.publisher.service;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;

import java.util.List;
import java.util.UUID;

public interface PublisherService {

    void save(Publisher publisher);
    void saveAll(List<Publisher> publishers);
    Publisher find(UUID id);
    List<Publisher> findAll();
    void update(UUID id, Publisher updated);
    void delete(UUID id);

}
