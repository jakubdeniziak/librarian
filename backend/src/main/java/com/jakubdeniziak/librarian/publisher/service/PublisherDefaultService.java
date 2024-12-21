package com.jakubdeniziak.librarian.publisher.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherDeprecatedMapper;
import com.jakubdeniziak.librarian.publisher.repository.PublisherJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PublisherDefaultService implements PublisherService {

    private final PublisherJpaRepository repository;
    private final PublisherDeprecatedMapper mapper;

    @Override
    public void save(Publisher publisher) {
        repository.save(mapper.mapToEntity(publisher));
    }

    @Override
    public Optional<Publisher> find(UUID id) {
        return mapper.mapToOptional(repository.findById(id));
    }

    @Override
    public List<Publisher> findAll() {
        return mapper.mapEntities(repository.findAll());
    }

    @Override
    public void update(UUID id, Publisher updated) {
        Publisher publisher = find(id).orElseThrow(ResourceNotFoundException::new);
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
