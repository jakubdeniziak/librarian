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
    @PutMapping("/books/{id}")
    public void create(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        bookService.save(mapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @GetMapping("/books/{id}")
    public BookResponse read(@PathVariable("id") UUID id) {
        return mapper.mapToResponse(bookService.find(id));
    }

    @Override
    @GetMapping("/authors/{id}/books")
    public BooksResponse readAllByAuthor(@PathVariable("id") UUID authorId) {
        return mapper.mapToResponse(bookService.findAllByAuthor(authorId));
    }

    @Override
    @GetMapping("/publishers/{id}/books")
    public BooksResponse readAllByPublisher(@PathVariable("id") UUID publisherId) {
        return mapper.mapToResponse(bookService.findAllByPublisher(publisherId));
    }

    @Override
    @GetMapping("/books")
    public BooksResponse readAll() {
        return mapper.mapToResponse(bookService.findAll());
    }

    @Override
    @PatchMapping("/books/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        bookService.update(id, mapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
    }

}
