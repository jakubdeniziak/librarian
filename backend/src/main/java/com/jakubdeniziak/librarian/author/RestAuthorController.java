package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class RestAuthorController implements AuthorController {
    private final AuthorService service;
    private final AuthorMapper mapper;

    @Override
    @GetMapping("/{id}")
    public AuthorResponse readAuthor(@PathVariable("id") UUID id) {
        Author author = service.findById(id);
        return mapper.map(author);
    }

    @Override
    @GetMapping
    public AuthorsResponse readAuthors() {
        List<Author> authors = service.findAll();
        return mapper.map(authors);
    }

    @Override
    @PutMapping("/{id}")
    public void createAuthor(@PathVariable("id") UUID id, @RequestBody AuthorRequest request) {
        Author author = mapper.map(id, request);
        service.save(author);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateAuthor(@PathVariable("id") UUID id, @RequestBody AuthorRequest request) {
        Author author = mapper.map(id, request);
        service.update(id, author);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
