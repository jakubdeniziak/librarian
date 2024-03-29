package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PublisherService {
    private final PublisherRepository repository;

    public Publisher findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publisher id: " + id + "not found"));
    }

    public void save(Publisher publisher) {
        repository.save(publisher);
    }
}
