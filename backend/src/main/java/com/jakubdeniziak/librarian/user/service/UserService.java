package com.jakubdeniziak.librarian.user.service;

import com.jakubdeniziak.librarian.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void save(User user);
    void saveAll(List<User> users);
    User find(UUID id);
    List<User> findAll();

}
