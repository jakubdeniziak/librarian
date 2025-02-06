package com.jakubdeniziak.librarian.book.dto.response.book;

import com.jakubdeniziak.librarian.book.entity.BookFormat;

import java.util.UUID;

public interface BookResponse {

    UUID getId();
    String getIsbn();
    String getTitle();
    String getDescription();
    BookFormat getFormat();
    UUID getAuthorId();
    UUID getPublisherId();


}
