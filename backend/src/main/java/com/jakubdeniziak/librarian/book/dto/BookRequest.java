package com.jakubdeniziak.librarian.book.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class BookRequest {

    String isbn;
    String title;
    String description;
    UUID authorId;
    UUID publisherId;

}
