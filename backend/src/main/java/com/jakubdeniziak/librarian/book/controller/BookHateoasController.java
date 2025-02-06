package com.jakubdeniziak.librarian.book.controller;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.book.dto.response.books.BooksResponse;
import com.jakubdeniziak.librarian.book.mapper.BookRequestToDomainMapper;
import com.jakubdeniziak.librarian.book.mapper.hateoas.response.BookHateoasResponseMapper;
import com.jakubdeniziak.librarian.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class BookHateoasController implements BookController {

    public static final String BOOKS_HATEOAS_ENDPOINT = "/v2/books";

    private final BookService bookService;
    private final BookRequestToDomainMapper requestToDomainMapper;
    private final BookHateoasResponseMapper hateoasMapper;

    @Override
    @PutMapping("/v2/books/{id}")
    public void create(@PathVariable UUID id, @RequestBody BookRequest request) {
        bookService.save(requestToDomainMapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @GetMapping("/v2/books/{id}")
    public BookResponse read(@PathVariable UUID id) {
        return hateoasMapper.mapToResponse(bookService.find(id));
    }

    @Override
    @GetMapping("/v2/authors/{authorId}/books")
    public BooksResponse readAllByAuthor(@PathVariable UUID authorId) {
        // TODO: implement
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @GetMapping("/v2/publishers/{publisherId}/books")
    public BooksResponse readAllByPublisher(@PathVariable UUID publisherId) {
        // TODO: implement
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @GetMapping("/v2/books")
    public BooksResponse readAll() {
        // TODO: implement
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @PatchMapping("/v2/books/{id}")
    public void update(@PathVariable UUID id, @RequestBody BookRequest request) {
        bookService.update(id, requestToDomainMapper.map(id, request), request.getAuthorId(), request.getPublisherId());
    }

    @Override
    @DeleteMapping("/v2/books/{id}")
    public void delete(@PathVariable UUID id) {
        bookService.delete(id);
    }

}
