package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import com.jakubdeniziak.librarian.book.repository.BookJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookDefaultServiceTest {

    private static final UUID BOOK_ID = UUID.fromString("8cc25a27-3f36-485e-ba59-4cf3109e371d");
    private static final String BOOK_TITLE = "Book Title";
    private static final BookEntity BOOK_ENTITY = BookEntity.builder()
            .id(BOOK_ID)
            .title(BOOK_TITLE)
            .build();
    private static final Book BOOK = Book.builder()
            .id(BOOK_ID)
            .title(BOOK_TITLE)
            .build();

    @Mock
    private BookJpaRepository repository;
    @Mock
    private BookMapper mapper;
    @InjectMocks
    private BookDefaultService bookService;

    @Test
    public void shouldFindBook() {
        when(repository.findById(BOOK_ID)).thenReturn(Optional.of(BOOK_ENTITY));
        when(mapper.mapToDomain(BOOK_ENTITY)).thenReturn(BOOK);

        Book result = bookService.find(BOOK_ID);

        assertThat(result)
                .returns(BOOK_ID, Book::getId);

        verify(repository).findById(BOOK_ID);
        verify(mapper).mapToDomain(BOOK_ENTITY);
    }

    @Test
    public void shouldDeleteBook() {
        bookService.delete(BOOK_ID);

        verify(repository).deleteById(BOOK_ID);
    }

}
