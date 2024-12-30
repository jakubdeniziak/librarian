package com.jakubdeniziak.librarian.data.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class DataResponse {

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

    List<Author> authors;
    List<Publisher> publishers;
    List<Library> libraries;
    List<Book> books;
    List<LibraryBook> libraryBooks;

}
