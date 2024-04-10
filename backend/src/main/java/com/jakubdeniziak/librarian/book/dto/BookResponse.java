package com.jakubdeniziak.librarian.book.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class BookResponse {
    private UUID id;
    private String isbn;
    private String title;
    private String description;
    private UUID authorId;
    private UUID publisherId;
}
