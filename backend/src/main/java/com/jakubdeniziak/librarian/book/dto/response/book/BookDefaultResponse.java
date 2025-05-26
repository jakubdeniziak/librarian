package com.jakubdeniziak.librarian.book.dto.response.book;

import com.jakubdeniziak.librarian.book.entity.BookFormat;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BookDefaultResponse implements BookResponse {

    UUID id;
    String isbn;
    String title;
    String description;
    BookFormat format;
    UUID authorId;
    UUID publisherId;

}
