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
@RequestMapping("/libraries/")
@AllArgsConstructor
public class LibraryBookRestController implements LibraryBookController {

    private final LibraryBookService service;
    private final LibraryBookMapper mapper;

    @Override
    @PutMapping("/{library_id}/books/{book_id}")
    public void create(@PathVariable("library_id") UUID libraryId, @PathVariable("book_id") UUID bookId, @RequestBody LibraryBookRequest request) {
        service.save(mapper.map(bookId, libraryId, request));
    }

    @Override
    @GetMapping("/{library_id}/books/{book_id}")
    public LibraryBookResponse read(@PathVariable("library_id") UUID libraryId, @PathVariable("book_id") UUID bookId) {
        return mapper.mapToResponse(service.find(libraryId, bookId));
    }

    @Override
    @GetMapping("/{id}/books")
    public LibraryBooksResponse readAllByLibrary(@PathVariable("id") UUID libraryId) {
        return mapper.mapToResponse(service.findAllByLibrary(libraryId));
    }

    @Override
    @GetMapping("/books")
    public LibraryBooksResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @PatchMapping("/{library_id}/books/{book_id}")
    public void update(@PathVariable("library_id") UUID libraryId, @PathVariable("book_id") UUID bookId, @RequestBody LibraryBookRequest request) {
        service.update(libraryId, bookId, mapper.map(libraryId, bookId, request));
    }

    @Override
    @DeleteMapping("/{library_id}/books/{book_id}")
    public void delete(@PathVariable("library_id") UUID libraryId, @PathVariable("book_id") UUID bookId) {
        service.delete(bookId, libraryId);
    }

}
