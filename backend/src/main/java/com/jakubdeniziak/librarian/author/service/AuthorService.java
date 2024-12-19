package com.jakubdeniziak.librarian.author.service;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {

    void save(AuthorEntity authorEntity);
    Optional<AuthorEntity> find(UUID id);
    List<AuthorEntity> findAll();
    void update(UUID id, AuthorEntity updated);
    void delete(UUID id);

}
