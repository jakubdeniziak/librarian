package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PublisherService {
    private final PublisherRepository repository;

    public Publisher findById(UUID id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Publisher> findAll() {
        return repository.findAll();
    }

    public void save(Publisher publisher) {
        repository.save(publisher);
    }

    public void update(UUID id, Publisher updatedPublisher) {
        Publisher publisher = findById(id);

        if (updatedPublisher.getWebsiteUrl() != null) {
            publisher.setWebsiteUrl(updatedPublisher.getWebsiteUrl());
        }
        if (updatedPublisher.getDescription() != null) {
            publisher.setDescription(updatedPublisher.getDescription());
        }

        save(publisher);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
