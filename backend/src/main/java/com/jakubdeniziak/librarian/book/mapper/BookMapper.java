package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.BookRequest;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
import com.jakubdeniziak.librarian.book.entity.BookEntity;

import java.util.List;
import java.util.UUID;

public interface BookMapper {

    Book map(UUID id, BookRequest request);
    BookEntity map(Book book);
    Book map(BookEntity book);
    List<Book> map(List<BookEntity> books);
    BookResponse mapToResponse(Book book);
    BooksResponse mapToResponse(List<Book> books);

}
