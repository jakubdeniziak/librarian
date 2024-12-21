package com.jakubdeniziak.librarian.library.controller;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.mapper.LibraryDeprecatedMapper;
import com.jakubdeniziak.librarian.library.service.LibraryDefaultService;
import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/libraries")
@AllArgsConstructor
public class LibraryRestController implements LibraryController {

    private final LibraryDefaultService service;
    private final LibraryDeprecatedMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable("id") UUID id, @RequestBody LibraryRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public LibraryResponse read(@PathVariable("id") UUID id) {
        Library library = service.find(id).orElseThrow(ResourceNotFoundException::new);
        return mapper.map(library);
    }

    @Override
    @GetMapping
    public LibrariesResponse readAll() {
        return mapper.map(service.findAll());
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody LibraryRequest request) {
        service.update(id, mapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
