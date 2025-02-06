package com.jakubdeniziak.librarian.author.mapper.hateoas;

import com.jakubdeniziak.librarian.author.controller.AuthorHateoasController;
import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorHateoasResponse;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.book.controller.BookController;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@AllArgsConstructor
public class AuthorDefaultHateoasMapper implements AuthorHateoasMapper {

    private final BookMapper bookMapper;

    @Override
    public AuthorResponse mapToResponse(Author author, List<Book> authorBooks) {
        AuthorHateoasResponse response = AuthorHateoasResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .books(mapBooks(authorBooks))
                .build();
        response.add(
                linkTo(AuthorHateoasController.class)
                    .slash(response.getId())
                    .withSelfRel()
        );
        response.add(linkTo(BookController.class)
                .slash("/v1/books")
                .withRel("books"));
        return response;
    }

    // TODO: use BookHateoasMapper once it is created
    private CollectionModel<EntityModel<BookResponse>> mapBooks(List<Book> authorBooks) {
        return CollectionModel.of(authorBooks.stream()
                .map(bookMapper::mapToResponse)
                .map(book -> EntityModel.of(book, linkTo(BookController.class)
                        .slash("/v1/books/%s".formatted(book.getId()))
                        .withSelfRel()))
                .toList());
    }

}
