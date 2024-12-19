package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.author.dto.AuthorRequest;
import com.jakubdeniziak.librarian.author.dto.AuthorResponse;
import com.jakubdeniziak.librarian.author.dto.AuthorsResponse;
import com.jakubdeniziak.librarian.book.Book;
import com.jakubdeniziak.librarian.book.BookMapper;
import com.jakubdeniziak.librarian.book.BookService;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class RestAuthorController implements AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @Override
    @GetMapping("/{id}")
    public AuthorResponse readAuthor(@PathVariable("id") UUID id) {
        Author author = authorService.findById(id);
        return authorMapper.map(author);
    }

    @Override
    @GetMapping
    public AuthorsResponse readAuthors() {
        List<Author> authors = authorService.findAll();
        return authorMapper.map(authors);
    }

    @Override
    @GetMapping("/{id}/books")
    public BooksResponse readBooksByAuthor(@PathVariable("id") UUID id) {
        List<Book> books = bookService.findAllByAuthorId(id);
        return bookMapper.map(books);
    }

    @Override
    @PutMapping("/{id}")
    public void createAuthor(@PathVariable("id") UUID id, @RequestBody AuthorRequest request) {
        Author author = authorMapper.map(id, request);
        authorService.save(author);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateAuthor(@PathVariable("id") UUID id, @RequestBody AuthorRequest request) {
        Author author = authorMapper.map(id, request);
        authorService.update(id, author);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") UUID id) {
        authorService.delete(id);
    }
}
