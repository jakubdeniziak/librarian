package com.jakubdeniziak.librarian.book.mapper.hateoas.response;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;

public interface BookHateoasResponseMapper {

    BookResponse mapToResponse(Book book);

}
