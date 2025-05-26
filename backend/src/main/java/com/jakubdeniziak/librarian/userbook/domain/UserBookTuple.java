package com.jakubdeniziak.librarian.userbook.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UserBookTuple {

    UserBook userBook;
    UUID userId;
    UUID bookId;

}
