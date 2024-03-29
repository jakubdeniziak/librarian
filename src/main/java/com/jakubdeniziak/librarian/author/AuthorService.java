package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public Author findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author id: " + id + "not found"));
    }

    public void save(Author author) {
        repository.save(author);
    }
}
