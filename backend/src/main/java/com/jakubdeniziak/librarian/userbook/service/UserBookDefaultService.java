package com.jakubdeniziak.librarian.userbook.service;

import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.user.service.UserService;
import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.mapper.UserBookMapper;
import com.jakubdeniziak.librarian.userbook.repository.UserBookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserBookDefaultService implements UserBookService {

    private final UserBookJpaRepository repository;
    private final UserBookMapper mapper;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public void save(UserBook userBook, UUID userId, UUID bookId) {
        userBook.setUser(userService.find(userId));
        userBook.setBook(bookService.find(bookId));
        repository.save(mapper.map(userBook));
    }

    @Override
    public UserBook find(UUID id) {
        return mapper.map(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<UserBook> findAllByUser(UUID userId) {
        return mapper.map(repository.findAllByUserId(userId));
    }

    @Override
    public List<UserBook> findAll() {
        return mapper.map(repository.findAll());
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
