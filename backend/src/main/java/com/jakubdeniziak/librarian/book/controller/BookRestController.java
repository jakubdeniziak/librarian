package com.jakubdeniziak.librarian.book.controller;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.book.mapper.BookDeprecatedMapper;
import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class BookRestController implements BookController {

    private final BookService bookDefaultService;
    private final BookDeprecatedMapper mapper;

    @Override
    @PutMapping("/books/{id}")
    public void create(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        BookEntity bookEntity = mapper.map(id, request);
        bookDefaultService.save(bookEntity);
    }

    @Override
    @GetMapping("/books/{id}")
    public BookResponse read(@PathVariable("id") UUID id) {
        BookEntity bookEntity = bookDefaultService.findById(id);
        return mapper.map(bookEntity);
    }

    @Override
    @GetMapping("/authors/{id}/books")
    public BooksResponse readAllByAuthor(@PathVariable("id") UUID authorId) {
        List<BookEntity> bookEntities = bookDefaultService.findAllByAuthorId(authorId);
        return mapper.map(bookEntities);
    }

    @Override
    @GetMapping("/publishers/{id}/books")
    public BooksResponse readBooksByPublisher(@PathVariable("id") UUID publisherId) {
        List<BookEntity> bookEntities = bookDefaultService.findAllByPublisherId(publisherId);
        return bookDeprecatedMapper.map(bookEntities);
    }

    @Override
    @GetMapping("/books")
    public BooksResponse readAll() {
        List<BookEntity> bookEntities = bookDefaultService.findAll();
        return mapper.map(bookEntities);
    }

    @Override
    @PatchMapping("/books/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        BookEntity bookEntity = mapper.map(id, request);
        bookDefaultService.update(id, bookEntity);
    }

    @Override
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") UUID id) {
        bookDefaultService.delete(id);
    }

}
