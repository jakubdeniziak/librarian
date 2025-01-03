package com.jakubdeniziak.librarian.userbook.dto;

import com.jakubdeniziak.librarian.userbook.entity.ReadingStatus;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class UserBookRequest {

    LocalDateTime startedOn;
    LocalDateTime finishedOn;
    Float rating;
    String review;
    ReadingStatus readingStatus;

}
