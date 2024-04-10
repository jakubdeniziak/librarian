package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public Author findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author id: " + id + "not found"));
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public void save(Author author) {
        repository.save(author);
    }

    public void update(UUID id, Author updatedAuthor) {
        Author author = findById(id);

        if (updatedAuthor.getFirstName() != null) {
            author.setFirstName(updatedAuthor.getFirstName());
        }
        if (updatedAuthor.getLastName() != null) {
            author.setLastName(updatedAuthor.getLastName());
        }

        save(author);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
