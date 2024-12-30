package com.jakubdeniziak.librarian.user.controller;

import com.jakubdeniziak.librarian.user.dto.UserRequest;
import com.jakubdeniziak.librarian.user.dto.UserResponse;
import com.jakubdeniziak.librarian.user.dto.UsersResponse;
import com.jakubdeniziak.librarian.user.mapper.UserMapper;
import com.jakubdeniziak.librarian.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserDefaultController implements UserController {

    private final UserService service;
    private final UserMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, UserRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public UserResponse read(@PathVariable UUID id) {
        return mapper.mapToResponse(service.find(id));
    }

    @Override
    @GetMapping
    public UsersResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

}
