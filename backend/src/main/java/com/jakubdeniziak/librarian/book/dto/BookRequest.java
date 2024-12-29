package com.jakubdeniziak.librarian.book.dto;

import com.jakubdeniziak.librarian.book.entity.BookFormat;
import lombok.Value;

import java.util.UUID;

@Value
public class BookRequest {

    String isbn;
    String title;
    String description;
    BookFormat format;
    UUID authorId;
    UUID publisherId;

}
