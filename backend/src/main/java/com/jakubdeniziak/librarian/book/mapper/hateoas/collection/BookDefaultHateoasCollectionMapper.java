package com.jakubdeniziak.librarian.book.mapper.hateoas.collection;

import com.jakubdeniziak.librarian.book.controller.BookHateoasController;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.book.mapper.BookDomainToResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.jakubdeniziak.librarian.book.controller.BookHateoasController.BOOKS_HATEOAS_ENDPOINT;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@AllArgsConstructor
public class BookDefaultHateoasCollectionMapper implements BookHateoasCollectionMapper {

    private final BookDomainToResponseMapper domainToResponseMapper;

    @Override
    public CollectionModel<EntityModel<BookResponse>> mapToPartialResponse(List<Book> books) {
        return CollectionModel.of(books.stream()
                .map(domainToResponseMapper::mapToResponse)
                .map(book -> EntityModel.of(book, linkTo(BookHateoasController.class)
                        .slash(BOOKS_HATEOAS_ENDPOINT)
                        .slash(book.getId())
                        .withSelfRel()))
                .toList());
    }

}
