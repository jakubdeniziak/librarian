package com.jakubdeniziak.librarian.userbook.service;

import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.domain.UserBookTuple;

import java.util.List;
import java.util.UUID;

public interface UserBookService {

    void save(UserBook userBook, UUID userId, UUID bookId);
    void saveAll(List<UserBookTuple> userBookTuples);
    UserBook find(UUID id);
    List<UserBook> findAllByUser(UUID userId);
    List<UserBook> findAll();
    void delete(UUID id);

}
