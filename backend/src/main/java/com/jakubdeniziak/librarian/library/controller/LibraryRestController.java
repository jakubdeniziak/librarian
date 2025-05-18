package com.jakubdeniziak.librarian.library.controller;

import com.jakubdeniziak.librarian.library.mapper.LibraryDefaultMapper;
import com.jakubdeniziak.librarian.library.service.LibraryDefaultService;
import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/libraries")
@AllArgsConstructor
public class LibraryRestController implements LibraryController {

    private final LibraryDefaultService service;
    private final LibraryDefaultMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @Valid @RequestBody LibraryRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public LibraryResponse read(@PathVariable UUID id) {
        return mapper.mapToResponse(service.find(id));
    }

    @Override
    @GetMapping
    public LibrariesResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @GetMapping("/count")
    public Integer getCount() {
        return service.getCount();
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
