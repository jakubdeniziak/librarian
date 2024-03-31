package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.author.Author;
import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.publisher.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookMapper {
    public BookResponse map(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .build();
    }

    public BooksResponse map(List<Book> books) {
        return BooksResponse.builder()
                .books(books.stream()
                        .map(book -> BooksResponse.Book.builder()
                                .id(book.getId())
                                .isbn(book.getIsbn())
                                .title(book.getTitle())
                                .authorId(book.getAuthor().getId())
                                .publisherId(book.getPublisher().getId())
                                .build()
                        ).toList()
                ).build();
    }

    public Book map(UUID id, BookRequest request) {
        return Book.builder()
                .id(id)
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .author(Author.builder()
                        .id(request.getAuthor())
                        .build())
                .publisher(Publisher.builder()
                        .id(request.getPublisher())
                        .build())
                .build();
    }
}
