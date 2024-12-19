package com.jakubdeniziak.librarian.author.service;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import com.jakubdeniziak.librarian.author.repository.AuthorJpaRepository;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultAuthorService implements AuthorService {

    private final AuthorJpaRepository repository;

    public void save(AuthorEntity authorEntity) {
        repository.save(authorEntity);
    }

    public Optional<AuthorEntity> find(UUID id) {
        return repository.findById(id);
    }

    public List<AuthorEntity> findAll() {
        return repository.findAll();
    }

    public void update(UUID id, AuthorEntity updated) {
        AuthorEntity authorEntity = find(id).orElseThrow(ResourceNotFoundException::new);

        if (updated.getFirstName() != null) {
            authorEntity.setFirstName(updated.getFirstName());
        }
        if (updated.getLastName() != null) {
            authorEntity.setLastName(updated.getLastName());
        }

        save(authorEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
