package com.jakubdeniziak.librarian.data.dto;

import com.jakubdeniziak.librarian.book.entity.BookFormat;
import com.jakubdeniziak.librarian.userbook.entity.ReadingStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public class DataFormat {

    @Value
    @Builder
    public static class Author {

        UUID id;
        String firstName;
        String lastName;
        String description;

    }

    @Value
    @Builder
    public static class Publisher {

        UUID id;
        String name;
        String websiteUrl;
        String description;

    }

    @Value
    @Builder
    public static class Library {

        UUID id;
        String name;
        String address;
        String description;

    }

    @Value
    @Builder
    public static class User {

        UUID id;
        String firstName;
        String lastName;
        String nickname;

    }

    @Value
    @Builder
    public static class Book {

        UUID id;
        String isbn;
        String title;
        String description;
        BookFormat format;
        UUID authorId;
        UUID publisherId;

    }

    @Value
    @Builder
    public static class LibraryBook {

        UUID libraryId;
        UUID bookId;
        Integer numberOfCopies;

    }

    @Value
    @Builder
    public static class UserBook {

        UUID id;
        UUID userId;
        UUID bookId;
        LocalDateTime startedOn;
        LocalDateTime finishedOn;
        Float rating;
        String review;
        ReadingStatus readingStatus;

    }

}
