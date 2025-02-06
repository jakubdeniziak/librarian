package com.jakubdeniziak.librarian.publisher.mapper.hateoas;

import com.jakubdeniziak.librarian.book.controller.BookHateoasController;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.mapper.hateoas.collection.BookHateoasCollectionMapper;
import com.jakubdeniziak.librarian.publisher.controller.PublisherHateoasController;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherHateoasResponse;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherDomainToResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.jakubdeniziak.librarian.book.controller.BookHateoasController.BOOKS_HATEOAS_ENDPOINT;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
@AllArgsConstructor
public class PublisherDefaultHateoasMapper implements PublisherHateoasMapper {

    private static final String BOOKS_LINK_REL = "books";

    private final PublisherDomainToResponseMapper domainToResponseMapper;
    private final BookHateoasCollectionMapper bookHateoasCollectionMapper;

    @Override
    public EntityModel<PublisherResponse> mapToPartialResponse(Publisher publisher) {
        PublisherResponse publisherResponse = domainToResponseMapper.mapToResponse(publisher);
        Link publisherLink = linkTo(PublisherHateoasController.class)
                .slash(publisher.getId())
                .withSelfRel();
        return EntityModel.of(publisherResponse, publisherLink);
    }

    @Override
    public PublisherResponse mapToResponse(Publisher publisher, List<Book> publisherBooks) {
        PublisherHateoasResponse response = PublisherHateoasResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .books(bookHateoasCollectionMapper.mapToPartialResponse(publisherBooks))
                .build();
        addLinks(response);
        return response;
    }

    private void addLinks(PublisherHateoasResponse response) {
        response.add(
                linkTo(PublisherHateoasController.class)
                        .slash(response.getId())
                        .withSelfRel(),
                linkTo(BookHateoasController.class)
                        .slash(BOOKS_HATEOAS_ENDPOINT)
                        .withRel(BOOKS_LINK_REL)
        );
    }

}
