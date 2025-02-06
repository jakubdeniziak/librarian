package com.jakubdeniziak.librarian.publisher.dto.response.publisher;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class PublisherHateoasResponse extends RepresentationModel<PublisherHateoasResponse> implements PublisherResponse {

    UUID id;
    String name;
    String websiteUrl;
    String description;

    @JsonUnwrapped
    CollectionModel<EntityModel<BookResponse>> books;

}
