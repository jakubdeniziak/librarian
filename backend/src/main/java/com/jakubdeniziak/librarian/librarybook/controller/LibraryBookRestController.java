package com.jakubdeniziak.librarian.librarybook.controller;

import com.jakubdeniziak.librarian.librarybook.mapper.LibraryBookMapper;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import com.jakubdeniziak.librarian.librarybook.service.LibraryBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/libraries/")
@AllArgsConstructor
public class LibraryBookRestController implements LibraryBookController {

    private final LibraryBookService service;
    private final LibraryBookMapper mapper;

    @Override
    @PutMapping("/{libraryId}/books/{bookId}")
    public void create(@PathVariable UUID libraryId, @PathVariable UUID bookId, @RequestBody LibraryBookRequest request) {
        service.save(mapper.map(request), libraryId, bookId);
    }

    @Override
    @GetMapping("/{libraryId}/books/{bookId}")
    public LibraryBookResponse read(@PathVariable UUID libraryId, @PathVariable UUID bookId) {
        return mapper.mapToResponse(service.find(libraryId, bookId));
    }

    @Override
    @GetMapping("/{libraryId}/books")
    public LibraryBooksResponse readAllByLibrary(@PathVariable UUID libraryId) {
        return mapper.mapToResponse(service.findAllByLibrary(libraryId));
    }

    @Override
    @GetMapping("/books")
    public LibraryBooksResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @PatchMapping("/{libraryId}/books/{bookId}")
    public void update(@PathVariable UUID libraryId, @PathVariable UUID bookId, @RequestBody LibraryBookRequest request) {
        service.update(libraryId, bookId, mapper.map(request));
    }

    @Override
    @DeleteMapping("/{libraryId}/books/{bookId}")
    public void delete(@PathVariable UUID libraryId, @PathVariable UUID bookId) {
        service.delete(bookId, libraryId);
    }

}
