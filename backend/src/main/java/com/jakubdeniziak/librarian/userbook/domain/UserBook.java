package com.jakubdeniziak.librarian.userbook.domain;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.userbook.entity.ReadingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserBook {

    private User user;
    private Book book;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Float rating;
    private String review;
    private ReadingStatus readingStatus;

}
