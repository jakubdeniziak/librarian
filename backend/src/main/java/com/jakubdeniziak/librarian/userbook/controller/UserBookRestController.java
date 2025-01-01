package com.jakubdeniziak.librarian.userbook.controller;

import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;
import com.jakubdeniziak.librarian.userbook.mapper.UserBookMapper;
import com.jakubdeniziak.librarian.userbook.service.UserBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserBookRestController implements UserBookController {

    private final UserBookService service;
    private final UserBookMapper mapper;

    @Override
    @PutMapping("/{userId}/books/{id}")
    public void create(@PathVariable UUID userId, @PathVariable UUID id, UserBookRequest request) {
        service.save(mapper.map(id, request), userId, request.getBookId());
    }

    @Override
    @GetMapping("/books/{id}")
    public UserBookResponse read(@PathVariable UUID id) {
        return mapper.mapToResponse(service.find(id));
    }

    @Override
    @GetMapping("{userId}/books")
    public UserBooksResponse readAllByUser(@PathVariable UUID userId) {
        return mapper.mapToResponse(service.findAllByUser(userId));
    }

    @Override
    @GetMapping("/books")
    public UserBooksResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
