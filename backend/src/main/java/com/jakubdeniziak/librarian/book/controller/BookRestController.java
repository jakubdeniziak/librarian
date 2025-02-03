package com.jakubdeniziak.librarian.book.controller;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import com.jakubdeniziak.librarian.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class BookRestController implements BookController {

    private final BookService bookService;
    private final BookMapper mapper;

    @Override
    @PutMapping("/v1/books/{id}")
    public void create(@PathVariable UUID id, @RequestBody BookRequest request) {
        bookService.save(mapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @GetMapping("/v1/books/{id}")
    public BookResponse read(@PathVariable UUID id) {
        return mapper.mapToResponse(bookService.find(id));
    }

    @Override
    @GetMapping("/v1/authors/{authorId}/books")
    public BooksResponse readAllByAuthor(@PathVariable UUID authorId) {
        return mapper.mapToResponse(bookService.findAllByAuthor(authorId));
    }

    @Override
    @GetMapping("/v1/publishers/{publisherId}/books")
    public BooksResponse readAllByPublisher(@PathVariable UUID publisherId) {
        return mapper.mapToResponse(bookService.findAllByPublisher(publisherId));
    }

    @Override
    @GetMapping("/v1/books")
    public BooksResponse readAll() {
        return mapper.mapToResponse(bookService.findAll());
    }

    @Override
    @PatchMapping("/v1/books/{id}")
    public void update(@PathVariable UUID id, @RequestBody BookRequest request) {
        bookService.update(id, mapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @DeleteMapping("/v1/books/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.delete(id);
    }

}
