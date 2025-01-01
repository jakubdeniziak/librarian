package com.jakubdeniziak.librarian.userbook.controller;

import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;

import java.util.UUID;

public interface UserBookController {

    void create(UUID userId, UUID id, UserBookRequest request);
    UserBookResponse read(UUID id);
    UserBooksResponse readAllByUser(UUID userId);
    UserBooksResponse readAll();
    void delete(UUID id);

}
