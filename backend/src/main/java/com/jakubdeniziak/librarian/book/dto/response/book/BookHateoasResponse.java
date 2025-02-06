package com.jakubdeniziak.librarian.book.dto.response.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorHateoasResponse;
import com.jakubdeniziak.librarian.author.dto.response.author.AuthorResponse;
import com.jakubdeniziak.librarian.book.entity.BookFormat;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class BookHateoasResponse extends RepresentationModel<AuthorHateoasResponse> implements BookResponse {

    UUID id;
    String isbn;
    String title;
    String description;
    BookFormat format;

    @JsonIgnore
    UUID authorId;
    @JsonIgnore
    UUID publisherId;

    EntityModel<AuthorResponse> author;
    EntityModel<PublisherResponse> publisher;

}
