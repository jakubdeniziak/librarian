package com.jakubdeniziak.librarian.author.controller;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.response.authors.AuthorsResponse;
import com.jakubdeniziak.librarian.author.mapper.AuthorRequestToDomainMapper;
import com.jakubdeniziak.librarian.author.mapper.hateoas.AuthorHateoasMapper;
import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v2/authors")
@AllArgsConstructor
public class AuthorHateoasController implements AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final AuthorRequestToDomainMapper requestToDomainMapper;
    private final AuthorHateoasMapper hateoasMapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @RequestBody AuthorRequest request) {
        authorService.save(requestToDomainMapper.map(id, request));
    }

    @Override
    @GetMapping(value = "/{id}", produces = { "application/hal+json" })
    public AuthorResponse read(@PathVariable UUID id) {
        Author author = authorService.find(id);
        List<Book> authorBooks = bookService.findAllByAuthor(id);
        return hateoasMapper.mapToResponse(author, authorBooks);
    }

    @Override
    @GetMapping
    public AuthorsResponse readAll() {
        // TODO: implement
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody AuthorRequest request) {
        authorService.update(id, requestToDomainMapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        authorService.delete(id);
    }

}
