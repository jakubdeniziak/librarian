package com.jakubdeniziak.librarian.userbook.mapper;

import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;
import com.jakubdeniziak.librarian.userbook.entity.UserBookEntity;

import java.util.List;

public interface UserBookMapper {

    UserBook map(UserBookRequest request);
    UserBookEntity map(UserBook userBook);
    List<UserBookEntity> map(List<UserBook> userBooks);
    UserBook mapToDomain(UserBookEntity userBook);
    List<UserBook> mapToDomain(List<UserBookEntity> userBooks);
    UserBookResponse mapToResponse(UserBook userBook);
    UserBooksResponse mapToResponse(List<UserBook> userBooks);

}
