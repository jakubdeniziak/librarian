package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryService {
    private final LibraryRepository repository;

    public Library findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Library id: " + id + "not found"));
    }

    public List<Library> findAll() {
        return repository.findAll();
    }

    public void save(Library library) {
        repository.save(library);
    }

    public void update(UUID id, Library updatedLibrary) {
        Library library = findById(id);

        if (updatedLibrary.getName() != null) {
            library.setName(updatedLibrary.getName());
        }
        if (updatedLibrary.getAddress() != null) {
            library.setAddress(updatedLibrary.getAddress());
        }

        save(library);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
