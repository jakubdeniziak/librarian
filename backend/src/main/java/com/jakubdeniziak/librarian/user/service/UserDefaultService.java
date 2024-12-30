package com.jakubdeniziak.librarian.user.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.user.mapper.UserMapper;
import com.jakubdeniziak.librarian.user.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserDefaultService implements UserService {

    private final UserJpaRepository repository;
    private final UserMapper mapper;

    @Override
    public void save(User user) {
        repository.save(mapper.map(user));
    }

    @Override
    public User find(UUID id) {
        return mapper.map(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<User> findAll() {
        return mapper.map(repository.findAll());
    }

}
