package com.jakubdeniziak.librarian.book.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Book {

    private UUID id;
    private String isbn;
    private String title;
    private String description;

}
