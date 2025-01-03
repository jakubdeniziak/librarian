package com.jakubdeniziak.librarian.data.dto;

import lombok.Builder;
import lombok.Value;

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
    public static class Book {

        UUID id;
        String isbn;
        String title;
        String description;
        String format;
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

}
