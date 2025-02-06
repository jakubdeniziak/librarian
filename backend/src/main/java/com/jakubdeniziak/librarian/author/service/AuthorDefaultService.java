package com.jakubdeniziak.librarian.author.service;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.mapper.AuthorDomainToEntityMapper;
import com.jakubdeniziak.librarian.author.mapper.AuthorEntityToDomainMapper;
import com.jakubdeniziak.librarian.author.repository.AuthorJpaRepository;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorDefaultService implements AuthorService {

    private final AuthorJpaRepository repository;
    private final AuthorDomainToEntityMapper domainToEntityMapper;
    private final AuthorEntityToDomainMapper entityToDomainMapper;

    @Override
    public void save(Author author) {
        repository.save(domainToEntityMapper.map(author));
    }

    @Override
    public void saveAll(List<Author> authors) {
        repository.saveAll(domainToEntityMapper.map(authors));
    }

    @Override
    public Author find(UUID id) {
        return entityToDomainMapper.mapToDomain(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Author> findAll() {
        return entityToDomainMapper.mapToDomain(repository.findAll());
    }

    @Override
    public void update(UUID id, Author updated) {
        Author author = find(id);

        if (updated.getFirstName() != null) {
            author.setFirstName(updated.getFirstName());
        }
        if (updated.getLastName() != null) {
            author.setLastName(updated.getLastName());
        }

        save(author);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
