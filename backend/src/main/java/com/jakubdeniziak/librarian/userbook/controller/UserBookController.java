package com.jakubdeniziak.librarian.userbook.controller;

import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;

import java.util.UUID;

public interface UserBookController {

    void create(UUID userId, UUID bookId, UserBookRequest request);
    UserBookResponse read(UUID userId, UUID bookId);
    UserBooksResponse readAllByUser(UUID id);
    UserBooksResponse readAll();
    void delete(UUID userId, UUID bookId);

}
