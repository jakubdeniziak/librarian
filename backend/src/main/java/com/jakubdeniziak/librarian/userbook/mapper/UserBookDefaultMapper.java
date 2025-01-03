package com.jakubdeniziak.librarian.userbook.mapper;

import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import com.jakubdeniziak.librarian.user.mapper.UserMapper;
import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.dto.UserBookRequest;
import com.jakubdeniziak.librarian.userbook.dto.UserBookResponse;
import com.jakubdeniziak.librarian.userbook.dto.UserBooksResponse;
import com.jakubdeniziak.librarian.userbook.entity.UserBookEntity;
import com.jakubdeniziak.librarian.userbook.entity.UserBookKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserBookDefaultMapper implements UserBookMapper {

    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Override
    public UserBook map(UserBookRequest request) {
        return UserBook.builder()
                .startedOn(request.getStartedOn())
                .finishedOn(request.getFinishedOn())
                .rating(request.getRating())
                .review(request.getReview())
                .readingStatus(request.getReadingStatus())
                .build();
    }

    @Override
    public UserBookEntity map(UserBook userBook) {
        return UserBookEntity.builder()
                .id(UserBookKey.builder()
                        .userId(userBook.getUser().getId())
                        .bookId(userBook.getBook().getId())
                        .build())
                .startedOn(userBook.getStartedOn())
                .finishedOn(userBook.getFinishedOn())
                .rating(userBook.getRating())
                .review(userBook.getReview())
                .readingStatus(userBook.getReadingStatus())
                .user(userMapper.map(userBook.getUser()))
                .book(bookMapper.map(userBook.getBook()))
                .build();
    }

    @Override
    public List<UserBookEntity> map(List<UserBook> userBooks) {
        return userBooks.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public UserBook mapToDomain(UserBookEntity userBook) {
        return UserBook.builder()
                .user(userMapper.mapToDomain(userBook.getUser()))
                .book(bookMapper.mapToDomain(userBook.getBook()))
                .startedOn(userBook.getStartedOn())
                .finishedOn(userBook.getFinishedOn())
                .rating(userBook.getRating())
                .review(userBook.getReview())
                .readingStatus(userBook.getReadingStatus())
                .build();
    }

    @Override
    public List<UserBook> mapToDomain(List<UserBookEntity> userBooks) {
        return userBooks.stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public UserBookResponse mapToResponse(UserBook userBook) {
        return UserBookResponse.builder()
                .userId(userBook.getUser().getId())
                .bookId(userBook.getBook().getId())
                .startedOn(userBook.getStartedOn())
                .finishedOn(userBook.getFinishedOn())
                .rating(userBook.getRating())
                .review(userBook.getReview())
                .readingStatus(userBook.getReadingStatus())
                .build();
    }

    @Override
    public UserBooksResponse mapToResponse(List<UserBook> userBooks) {
        List<UserBooksResponse.UserBook> responseUserBooks = userBooks.stream()
                .map(userBook -> UserBooksResponse.UserBook.builder()
                        .userId(userBook.getUser().getId())
                        .bookId(userBook.getBook().getId())
                        .build())
                .toList();
        return UserBooksResponse.builder()
                .userBooks(responseUserBooks)
                .build();
    }

}
