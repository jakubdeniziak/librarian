package com.jakubdeniziak.librarian.userbook.domain;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.userbook.entity.ReadingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class UserBook {

    private UUID id;
    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Float rating;
    private String review;
    private ReadingStatus readingStatus;
    private User user;
    private Book book;

}
