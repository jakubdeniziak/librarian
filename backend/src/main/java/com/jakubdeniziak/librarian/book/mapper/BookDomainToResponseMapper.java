package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.book.dto.response.books.BooksResponse;

import java.util.List;

public interface BookDomainToResponseMapper {

    BookResponse mapToResponse(Book book);
    BooksResponse mapToResponse(List<Book> books);

}
