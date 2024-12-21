package com.jakubdeniziak.librarian.book.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BookResponse {

    UUID id;
    String isbn;
    String title;
    String description;
    UUID authorId;
    UUID publisherId;

}
