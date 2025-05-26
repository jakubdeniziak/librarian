package com.jakubdeniziak.librarian.publisher.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherDomainToEntityMapper;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherEntityToDomainMapper;
import com.jakubdeniziak.librarian.publisher.repository.PublisherJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PublisherDefaultService implements PublisherService {

    private final PublisherJpaRepository repository;
    private final PublisherDomainToEntityMapper domainToEntityMapper;
    private final PublisherEntityToDomainMapper entityToDomainMapper;

    @Override
    public void save(Publisher publisher) {
        repository.save(domainToEntityMapper.map(publisher));
    }

    @Override
    public void saveAll(List<Publisher> publishers) {
        repository.saveAll(domainToEntityMapper.map(publishers));
    }

    @Override
    public Publisher find(UUID id) {
        return entityToDomainMapper.mapToDomain(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Publisher> findAll() {
        return entityToDomainMapper.mapToDomain(repository.findAll());
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(repository.count());
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
