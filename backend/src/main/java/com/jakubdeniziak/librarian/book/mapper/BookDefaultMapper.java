package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.author.mapper.AuthorDomainToEntityMapper;
import com.jakubdeniziak.librarian.author.mapper.AuthorEntityToDomainMapper;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BookDefaultMapper implements BookMapper {

    private final AuthorDomainToEntityMapper authorDomainToEntityMapper;
    private final AuthorEntityToDomainMapper authorEntityToDomainMapper;
    private final PublisherMapper publisherMapper;

    @Override
    public Book map(UUID id, BookRequest request) {
        return Book.builder()
                .id(id)
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .description(request.getDescription())
                .format(request.getFormat())
                .build();
    }

    @Override
    public BookEntity map(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .description(book.getDescription())
                .format(book.getFormat())
                .author(authorDomainToEntityMapper.map(book.getAuthor()))
                .publisher(publisherMapper.map(book.getPublisher()))
                .build();
    }

    @Override
    public List<BookEntity> map(List<Book> books) {
        return books.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public Book mapToDomain(BookEntity book) {
        return Book.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .description(book.getDescription())
                .format(book.getFormat())
                .author(authorEntityToDomainMapper.mapToDomain(book.getAuthor()))
                .publisher(publisherMapper.mapToDomain(book.getPublisher()))
                .build();
    }

    @Override
    public List<Book> mapToDomain(List<BookEntity> books) {
        return books.stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .description(book.getDescription())
                .format(book.getFormat())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .build();
    }

    @Override
    public BooksResponse mapToResponse(List<Book> books) {
        List<BooksResponse.Book> responseBooks = books.stream()
                .map(book -> BooksResponse.Book.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .build())
                .toList();
        return BooksResponse.builder()
                .books(responseBooks)
                .build();
    }

}
