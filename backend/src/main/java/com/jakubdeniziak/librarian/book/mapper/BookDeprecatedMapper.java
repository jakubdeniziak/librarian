package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Deprecated
public class BookDeprecatedMapper {

    public BookResponse map(BookEntity bookEntity) {
        return BookResponse.builder()
                .id(bookEntity.getId())
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .description(bookEntity.getDescription())
                .authorId(bookEntity.getAuthorEntity().getId())
                .publisherId(bookEntity.getPublisherEntity().getId())
                .build();
    }

    public BooksResponse map(List<BookEntity> bookEntities) {
        return BooksResponse.builder()
                .books(bookEntities.stream()
                        .map(book -> BooksResponse.Book.builder()
                                .id(book.getId())
                                .title(book.getTitle())
                                .build()
                        ).toList()
                ).build();
    }

    public BookEntity map(UUID id, BookRequest request) {
        return BookEntity.builder()
                .id(id)
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .description(request.getDescription())
                .authorEntity(AuthorEntity.builder()
                        .id(request.getAuthorId())
                        .build())
                .publisherEntity(PublisherEntity.builder()
                        .id(request.getPublisherId())
                        .build())
                .build();
    }

}
