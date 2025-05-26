package com.jakubdeniziak.librarian.user.controller;

import com.jakubdeniziak.librarian.user.dto.UserRequest;
import com.jakubdeniziak.librarian.user.dto.UserResponse;
import com.jakubdeniziak.librarian.user.dto.UsersResponse;

import java.util.UUID;

public interface UserController {

    void create(UUID id, UserRequest request);
    UserResponse read(UUID id);
    UsersResponse readAll();

}
