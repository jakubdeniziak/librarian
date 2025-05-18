package com.jakubdeniziak.librarian.book.controller;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.book.dto.response.books.BooksResponse;
import com.jakubdeniziak.librarian.book.mapper.BookDomainToResponseMapper;
import com.jakubdeniziak.librarian.book.mapper.BookRequestToDomainMapper;
import com.jakubdeniziak.librarian.book.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class BookRestController implements BookController {

    private final BookService bookService;
    private final BookDomainToResponseMapper domainToResponseMapper;
    private final BookRequestToDomainMapper requestToDomainMapper;

    @Override
    @PutMapping("/v1/books/{id}")
    public void create(@PathVariable UUID id, @Valid @RequestBody BookRequest request) {
        bookService.save(requestToDomainMapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @GetMapping("/v1/books/{id}")
    public BookResponse read(@PathVariable UUID id) {
        return domainToResponseMapper.mapToResponse(bookService.find(id));
    }

    @Override
    @GetMapping("/v1/authors/{authorId}/books")
    public BooksResponse readAllByAuthor(@PathVariable UUID authorId) {
        return domainToResponseMapper.mapToResponse(bookService.findAllByAuthor(authorId));
    }

    @Override
    @GetMapping("/v1/publishers/{publisherId}/books")
    public BooksResponse readAllByPublisher(@PathVariable UUID publisherId) {
        return domainToResponseMapper.mapToResponse(bookService.findAllByPublisher(publisherId));
    }

    @Override
    @GetMapping("/v1/books")
    public BooksResponse readAll() {
        return domainToResponseMapper.mapToResponse(bookService.findAll());
    }

    @Override
    @GetMapping("/v1/books/count")
    public Integer getCount() {
        return bookService.getCount();
    }

    @Override
    @PatchMapping("/v1/books/{id}")
    public void update(@PathVariable UUID id, @RequestBody BookRequest request) {
        bookService.update(id, requestToDomainMapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @DeleteMapping("/v1/books/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.delete(id);
    }

}
