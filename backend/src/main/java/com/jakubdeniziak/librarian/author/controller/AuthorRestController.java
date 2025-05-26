package com.jakubdeniziak.librarian.author.controller;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.response.authors.AuthorsResponse;
import com.jakubdeniziak.librarian.author.mapper.AuthorDomainToResponseMapper;
import com.jakubdeniziak.librarian.author.mapper.AuthorRequestToDomainMapper;
import com.jakubdeniziak.librarian.author.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/authors")
@AllArgsConstructor
public class AuthorRestController implements AuthorController {

    private final AuthorService service;
    private final AuthorDomainToResponseMapper domainToResponseMapper;
    private final AuthorRequestToDomainMapper requestToDomainMapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @Valid @RequestBody AuthorRequest request) {
        service.save(requestToDomainMapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public AuthorResponse read(@PathVariable UUID id) {
        Author author = service.find(id);
        return domainToResponseMapper.mapToResponse(author);
    }

    @Override
    @GetMapping
    public AuthorsResponse readAll() {
        return domainToResponseMapper.mapToResponse(service.findAll());
    }

    @Override
    @GetMapping("/count")
    public Integer getCount() {
        return service.getCount();
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody AuthorRequest request) {
        service.update(id, requestToDomainMapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
