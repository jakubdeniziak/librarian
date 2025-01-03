package com.jakubdeniziak.librarian.userbook.dto;

import com.jakubdeniziak.librarian.userbook.entity.ReadingStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class UserBookResponse {

    UUID userId;
    UUID bookId;
    LocalDateTime startedOn;
    LocalDateTime finishedOn;
    Float rating;
    String review;
    ReadingStatus readingStatus;

}
