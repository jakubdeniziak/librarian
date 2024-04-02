package com.jakubdeniziak.librarian.book.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BookRequest {
    private String isbn;
    private String title;
    private String description;
    private UUID author;
    private UUID publisher;
}
