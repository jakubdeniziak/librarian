package com.jakubdeniziak.librarian.book.domain;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.book.entity.BookFormat;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Book {

    private UUID id;
    private String isbn;
    private String title;
    private String description;
    private BookFormat format;
    private Author author;
    private Publisher publisher;

}
