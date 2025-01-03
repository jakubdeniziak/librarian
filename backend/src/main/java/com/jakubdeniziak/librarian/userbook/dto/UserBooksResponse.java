package com.jakubdeniziak.librarian.userbook.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class UserBooksResponse {

    @Value
    @Builder
    public static class UserBook {

        UUID userId;
        UUID bookId;

    }

    List<UserBook> userBooks;

}
