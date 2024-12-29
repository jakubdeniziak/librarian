package com.jakubdeniziak.librarian.library.service;

import com.jakubdeniziak.librarian.library.domain.Library;

import java.util.List;
import java.util.UUID;

public interface LibraryService {

    void save(Library author);
    Library find(UUID id);
    List<Library> findAll();
    void update(UUID id, Library updated);
    void delete(UUID id);

}
