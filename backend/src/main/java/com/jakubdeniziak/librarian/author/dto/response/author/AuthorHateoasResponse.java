package com.jakubdeniziak.librarian.author.dto.response.author;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.jakubdeniziak.librarian.book.dto.BookResponse;
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
public class AuthorHateoasResponse extends RepresentationModel<AuthorHateoasResponse> implements AuthorResponse {

    UUID id;
    String firstName;
    String lastName;
    String description;

    @JsonUnwrapped
    CollectionModel<EntityModel<BookResponse>> books;

}
