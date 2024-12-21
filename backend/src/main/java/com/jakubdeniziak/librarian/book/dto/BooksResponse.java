package com.jakubdeniziak.librarian.book.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class BooksResponse {

    @Value
    @Builder
    public static class Book {
        UUID id;
        String title;
    }

    List<Book> books;

}
