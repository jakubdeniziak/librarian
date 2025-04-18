package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.entity.BookEntity;

import java.util.List;

public interface BookDomainToEntityMapper {

    BookEntity map(Book book);
    List<BookEntity> map(List<Book> books);

}
