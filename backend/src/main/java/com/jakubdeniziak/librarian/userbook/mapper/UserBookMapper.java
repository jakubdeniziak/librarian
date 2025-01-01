package com.jakubdeniziak.librarian.userbook.mapper;

import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;
import com.jakubdeniziak.librarian.userbook.entity.UserBookEntity;

import java.util.List;
import java.util.UUID;

public interface UserBookMapper {

    UserBook map(UUID id, UserBookRequest request);
    UserBookEntity map(UserBook userBook);
    UserBook map(UserBookEntity userBook);
    List<UserBook> map(List<UserBookEntity> userBooks);
    UserBookResponse mapToResponse(UserBook userBook);
    UserBooksResponse mapToResponse(List<UserBook> userBooks);

}
