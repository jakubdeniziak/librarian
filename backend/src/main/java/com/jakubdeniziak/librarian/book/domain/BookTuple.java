package com.jakubdeniziak.librarian.book.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class BookTuple {

    Book book;
    UUID authorId;
    UUID publisherId;

}
