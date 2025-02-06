package com.jakubdeniziak.librarian.publisher.mapper.hateoas;

import com.jakubdeniziak.librarian.book.controller.BookController;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import com.jakubdeniziak.librarian.publisher.controller.PublisherHateoasController;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherHateoasResponse;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@AllArgsConstructor
public class PublisherDefaultHateoasMapper implements PublisherHateoasMapper {

    private final BookMapper bookMapper;

    @Override
    public PublisherResponse mapToResponse(Publisher publisher, List<Book> publisherBooks) {
        PublisherHateoasResponse response = PublisherHateoasResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .books(mapBooks(publisherBooks))
                .build();
        addLinks(response);
        return response;
    }

    // TODO: use BookHateoasMapper once it is created
    private CollectionModel<EntityModel<BookResponse>> mapBooks(List<Book> publisherBooks) {
        return CollectionModel.of(publisherBooks.stream()
                .map(bookMapper::mapToResponse)
                .map(book -> EntityModel.of(book, linkTo(BookController.class)
                        .slash("/v1/books/%s".formatted(book.getId()))
                        .withSelfRel()))
                .toList());
    }

    private void addLinks(PublisherHateoasResponse response) {
        response.add(linkTo(PublisherHateoasController.class)
                .slash(response.getId())
                .withSelfRel());
        response.add(linkTo(BookController.class)
                .slash("/v1/books")
                .withRel("books"));
    }

}
