package com.jakubdeniziak.librarian.book.mapper;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.BookRequest;

import java.util.UUID;

public interface BookRequestToDomainMapper {

    Book map(UUID id, BookRequest request);

}
