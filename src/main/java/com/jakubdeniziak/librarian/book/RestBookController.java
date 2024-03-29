package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class RestBookController implements BookController {
    private final BookService bookService;
    private final BookMapper mapper;

    @Override
    @GetMapping("/{id}")
    public BookResponse readBook(@PathVariable("id") UUID id) {
        Book book = bookService.findById(id);
        return mapper.map(book);
    }

    @Override
    @GetMapping
    public BooksResponse readBooks() {
        List<Book> books = bookService.findAll();
        return mapper.map(books);
    }

    @Override
    @PutMapping("/{id}")
    public void createBook(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        Book book = mapper.map(id, request);
        bookService.save(book);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateBook(@PathVariable("id") UUID id, @RequestBody BookRequest request) {
        Book book = mapper.map(id, request);
        bookService.update(id, book);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") UUID id) {
        bookService.delete(id);
    }
}
