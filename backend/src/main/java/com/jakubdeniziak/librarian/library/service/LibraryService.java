package com.jakubdeniziak.librarian.library.service;

import com.jakubdeniziak.librarian.library.domain.Library;

import java.util.List;
import java.util.UUID;

public interface LibraryService {

    void save(Library library);
    void saveAll(List<Library> libraries);
    Library find(UUID id);
    List<Library> findAll();
    Integer getCount();
    void update(UUID id, Library updated);
    void delete(UUID id);

}
