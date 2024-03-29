package com.jakubdeniziak.librarian.book.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class BooksResponse {
    private List<Book> books;

    @Builder
    @Getter
    public static class Book {
        private UUID id;
        private String isbn;
        private String title;
        private UUID authorId;
        private UUID publisherId;
    }
}
