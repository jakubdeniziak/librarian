package com.jakubdeniziak.librarian.author.mapper.hateoas;

import com.jakubdeniziak.librarian.author.controller.AuthorHateoasController;
import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorHateoasResponse;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.author.mapper.AuthorDomainToResponseMapper;
import com.jakubdeniziak.librarian.book.controller.BookHateoasController;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.mapper.hateoas.collection.BookHateoasCollectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.jakubdeniziak.librarian.book.controller.BookHateoasController.BOOKS_HATEOAS_ENDPOINT;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@AllArgsConstructor
public class AuthorDefaultHateoasMapper implements AuthorHateoasMapper {

    private static final String BOOKS_LINK_REL = "books";

    private final AuthorDomainToResponseMapper domainToResponseMapper;
    private final BookHateoasCollectionMapper bookHateoasCollectionMapper;

    @Override
    public EntityModel<AuthorResponse> mapToPartialResponse(Author author) {
        AuthorResponse authorResponse = domainToResponseMapper.mapToResponse(author);
        Link authorLink = linkTo(AuthorHateoasController.class)
                .slash(author.getId())
                .withSelfRel();
        return EntityModel.of(authorResponse, authorLink);
    }

    @Override
    public AuthorResponse mapToResponse(Author author, List<Book> authorBooks) {
        AuthorHateoasResponse response = AuthorHateoasResponse.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .description(author.getDescription())
                .books(bookHateoasCollectionMapper.mapToPartialResponse(authorBooks))
                .build();
        addLinks(response);
        return response;
    }

    private void addLinks(AuthorHateoasResponse response) {
        response.add(
                linkTo(AuthorHateoasController.class)
                        .slash(response.getId())
                        .withSelfRel(),
                linkTo(BookHateoasController.class)
                        .slash(BOOKS_HATEOAS_ENDPOINT)
                        .withRel(BOOKS_LINK_REL)
        );
    }

}
