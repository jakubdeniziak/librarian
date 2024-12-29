package com.jakubdeniziak.librarian.book.dto;

import com.jakubdeniziak.librarian.book.entity.BookFormat;
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
    BookFormat format;
    UUID authorId;
    UUID publisherId;

}
