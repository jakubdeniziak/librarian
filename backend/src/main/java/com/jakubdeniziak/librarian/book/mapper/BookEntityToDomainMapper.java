package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.entity.BookEntity;

import java.util.List;

public interface BookEntityToDomainMapper {

    Book mapToDomain(BookEntity book);
    List<Book> mapToDomain(List<BookEntity> books);

}
