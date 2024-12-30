package com.jakubdeniziak.librarian.data.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDefaultMapper implements DataMapper {

    @Override
    public DataResponse map(List<Author> authors, List<Publisher> publishers, List<Library> libraries, List<Book> books, List<LibraryBook> libraryBooks) {
        return DataResponse.builder()
                .authors(mapAuthors(authors))
                .publishers(mapPublishers(publishers))
                .libraries(mapLibraries(libraries))
                .books(mapBooks(books))
                .libraryBooks(mapLibraryBooks(libraryBooks))
                .build();
    }

    private List<DataResponse.Author> mapAuthors(List<Author> authors) {
        return authors.stream()
                .map(author -> DataResponse.Author.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .description(author.getDescription())
                        .build())
                .toList();
    }

    private List<DataResponse.Publisher> mapPublishers(List<Publisher> publishers) {
        return publishers.stream()
                .map(publisher -> DataResponse.Publisher.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .websiteUrl(publisher.getWebsiteUrl())
                        .description(publisher.getDescription())
                        .build())
                .toList();
    }

    private List<DataResponse.Library> mapLibraries(List<Library> libraries) {
        return libraries.stream()
                .map(library -> DataResponse.Library.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .description(library.getDescription())
                        .build())
                .toList();
    }

    private List<DataResponse.Book> mapBooks(List<Book> books) {
        return books.stream()
                .map(book -> DataResponse.Book.builder()
                        .id(book.getId())
                        .isbn(book.getIsbn())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .format(book.getFormat().name())
                        .authorId(book.getAuthor().getId())
                        .publisherId(book.getPublisher().getId())
                        .build())
                .toList();
    }

    private List<DataResponse.LibraryBook> mapLibraryBooks(List<LibraryBook> libraryBooks) {
        return libraryBooks.stream()
                .map(libraryBook -> DataResponse.LibraryBook.builder()
                        .libraryId(libraryBook.getLibrary().getId())
                        .bookId(libraryBook.getBook().getId())
                        .numberOfCopies(libraryBook.getNumberOfCopies())
                        .build())
                .toList();
    }

}
