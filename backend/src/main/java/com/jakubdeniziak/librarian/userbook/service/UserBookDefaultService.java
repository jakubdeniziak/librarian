package com.jakubdeniziak.librarian.userbook.service;

import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.user.service.UserService;
import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.domain.UserBookTuple;
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
        UserBook initializedUserBook = getInitializedUserBook(userBook, userId, bookId);
        repository.save(mapper.map(initializedUserBook));
    }

    @Override
    public void saveAll(List<UserBookTuple> userBookTuples) {
        List<UserBook> initializedUserBooks = userBookTuples.stream()
                .map(userBook -> getInitializedUserBook(userBook.getUserBook(), userBook.getUserId(), userBook.getBookId()))
                .toList();
        repository.saveAll(mapper.map(initializedUserBooks));
    }

    @Override
    public UserBook find(UUID id) {
        return mapper.mapToDomain(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<UserBook> findAllByUser(UUID userId) {
        return mapper.mapToDomain(repository.findAllByUserId(userId));
    }

    @Override
    public List<UserBook> findAll() {
        return mapper.mapToDomain(repository.findAll());
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private UserBook getInitializedUserBook(UserBook userBook, UUID userId, UUID bookId) {
        userBook.setUser(userService.find(userId));
        userBook.setBook(bookService.find(bookId));
        return userBook;
    }

}
