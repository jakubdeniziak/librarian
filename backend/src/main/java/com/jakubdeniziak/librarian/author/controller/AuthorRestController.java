package com.jakubdeniziak.librarian.author.controller;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.mapper.AuthorMapper;
import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorRestController implements AuthorController {

    private final AuthorService service;
    private final AuthorMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @RequestBody AuthorRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public AuthorResponse read(@PathVariable UUID id) {
        Author author = service.find(id).orElseThrow(ResourceNotFoundException::new);
        return mapper.mapToResponse(author);
    }

    @Override
    @GetMapping
    public AuthorsResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody AuthorRequest request) {
        service.update(id, mapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
