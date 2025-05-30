package com.jakubdeniziak.librarian.author.mapper.hateoas;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.book.domain.Book;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface AuthorHateoasMapper {

    EntityModel<AuthorResponse> mapToPartialResponse(Author author);
    AuthorResponse mapToResponse(Author author, List<Book> authorBooks);

}
