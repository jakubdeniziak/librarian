package com.jakubdeniziak.librarian.publisher.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherMapper;
import com.jakubdeniziak.librarian.publisher.repository.PublisherJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PublisherDefaultService implements PublisherService {

    private final PublisherJpaRepository repository;
    private final PublisherMapper mapper;

    @Override
    public void save(Publisher publisher) {
        repository.save(mapper.map(publisher));
    }

    @Override
    public Publisher find(UUID id) {
        return mapper.map(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Publisher> findAll() {
        return mapper.map(repository.findAll());
    }

    @Override
    public void update(UUID id, Publisher updated) {
        Publisher publisher = find(id);
        if (updated.getName() != null) {
            publisher.setName(updated.getName());
        }
        if (updated.getWebsiteUrl() != null) {
            publisher.setWebsiteUrl(updated.getWebsiteUrl());
        }
        if (updated.getDescription() != null) {
            publisher.setDescription(updated.getDescription());
        }
        save(publisher);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
