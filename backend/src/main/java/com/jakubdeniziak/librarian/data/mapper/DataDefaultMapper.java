package com.jakubdeniziak.librarian.data.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.domain.BookTuple;
import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataFormat;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBookTuple;
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

    @Override
    public List<Author> mapAuthors(DataRequest request) {
        return request.getAuthors().stream()
                .map(author -> Author.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .description(author.getDescription())
                        .build())
                .toList();
    }

    @Override
    public List<Publisher> mapPublishers(DataRequest request) {
        return request.getPublishers().stream()
                .map(publisher -> Publisher.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .websiteUrl(publisher.getWebsiteUrl())
                        .description(publisher.getDescription())
                        .build())
                .toList();
    }

    @Override
    public List<Library> mapLibraries(DataRequest request) {
        return request.getLibraries().stream()
                .map(library -> Library.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .description(library.getDescription())
                        .build())
                .toList();
    }

    @Override
    public List<BookTuple> mapBooks(DataRequest request) {
        return request.getBooks().stream()
                .map(book -> BookTuple.builder()
                        .book(Book.builder()
                                .id(book.getId())
                                .isbn(book.getIsbn())
                                .title(book.getTitle())
                                .description(book.getDescription())
                                .format(book.getFormat())
                                .build())
                        .authorId(book.getAuthorId())
                        .publisherId(book.getPublisherId())
                        .build())
                .toList();
    }

    @Override
    public List<LibraryBookTuple> mapLibraryBooks(DataRequest request) {
        return request.getLibraryBooks().stream()
                .map(libraryBook -> LibraryBookTuple.builder()
                        .libraryBook(LibraryBook.builder()
                                .numberOfCopies(libraryBook.getNumberOfCopies())
                                .build())
                        .libraryId(libraryBook.getLibraryId())
                        .bookId(libraryBook.getBookId())
                        .build())
                .toList();
    }

    private List<DataFormat.Author> mapAuthors(List<Author> authors) {
        return authors.stream()
                .map(author -> DataFormat.Author.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .description(author.getDescription())
                        .build())
                .toList();
    }

    private List<DataFormat.Publisher> mapPublishers(List<Publisher> publishers) {
        return publishers.stream()
                .map(publisher -> DataFormat.Publisher.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .websiteUrl(publisher.getWebsiteUrl())
                        .description(publisher.getDescription())
                        .build())
                .toList();
    }

    private List<DataFormat.Library> mapLibraries(List<Library> libraries) {
        return libraries.stream()
                .map(library -> DataFormat.Library.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .description(library.getDescription())
                        .build())
                .toList();
    }

    private List<DataFormat.Book> mapBooks(List<Book> books) {
        return books.stream()
                .map(book -> DataFormat.Book.builder()
                        .id(book.getId())
                        .isbn(book.getIsbn())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .format(book.getFormat())
                        .authorId(book.getAuthor().getId())
                        .publisherId(book.getPublisher().getId())
                        .build())
                .toList();
    }

    private List<DataFormat.LibraryBook> mapLibraryBooks(List<LibraryBook> libraryBooks) {
        return libraryBooks.stream()
                .map(libraryBook -> DataFormat.LibraryBook.builder()
                        .libraryId(libraryBook.getLibrary().getId())
                        .bookId(libraryBook.getBook().getId())
                        .numberOfCopies(libraryBook.getNumberOfCopies())
                        .build())
                .toList();
    }

}
