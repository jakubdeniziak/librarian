package com.jakubdeniziak.librarian.library.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.mapper.LibraryDeprecatedMapper;
import com.jakubdeniziak.librarian.library.repository.LibraryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryDefaultService implements LibraryService {

    private final LibraryJpaRepository repository;
    private final LibraryDeprecatedMapper mapper;

    @Override
    public void save(Library library) {
        repository.save(mapper.mapToEntity(library));
    }

    @Override
    public Optional<Library> find(UUID id) {
        return mapper.map(repository.findById(id));
    }

    @Override
    public List<Library> findAll() {
        return mapper.mapToDomain(repository.findAll());
    }

    @Override
    public void update(UUID id, Library updated) {
        Library library = find(id).orElseThrow(ResourceNotFoundException::new);
        if (updated.getName() != null) {
            library.setName(updated.getName());
        }
        if (updated.getAddress() != null) {
            library.setAddress(updated.getAddress());
        }
        save(library);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
