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
import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.domain.UserBookTuple;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataDefaultMapper implements DataMapper {

    @Override
    public DataResponse map(List<Author> authors, List<Publisher> publishers, List<Library> libraries, List<User> users, List<Book> books, List<LibraryBook> libraryBooks, List<UserBook> userBooks) {
        return DataResponse.builder()
                .authors(mapAuthors(authors))
                .publishers(mapPublishers(publishers))
                .libraries(mapLibraries(libraries))
                .users(mapUsers(users))
                .books(mapBooks(books))
                .libraryBooks(mapLibraryBooks(libraryBooks))
                .userBooks(mapUserBooks(userBooks))
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
    public List<User> mapUsers(DataRequest request) {
        return request.getUsers().stream()
                .map(user -> User.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .nickname(user.getNickname())
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

    @Override
    public List<UserBookTuple> mapUserBooks(DataRequest request) {
        return request.getUserBooks().stream()
                .map(userBook -> UserBookTuple.builder()
                        .userBook(UserBook.builder()
                                .startedOn(userBook.getStartedOn())
                                .finishedOn(userBook.getFinishedOn())
                                .rating(userBook.getRating())
                                .review(userBook.getReview())
                                .readingStatus(userBook.getReadingStatus())
                                .build())
                        .userId(userBook.getUserId())
                        .bookId(userBook.getBookId())
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

    private List<DataFormat.User> mapUsers(List<User> users) {
        return users.stream()
                .map(user -> DataFormat.User.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .nickname(user.getNickname())
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

    private List<DataFormat.UserBook> mapUserBooks(List<UserBook> userBooks) {
        return userBooks.stream()
                .map(userBook -> DataFormat.UserBook.builder()
                        .userId(userBook.getUser().getId())
                        .bookId(userBook.getBook().getId())
                        .startedOn(userBook.getStartedOn())
                        .finishedOn(userBook.getFinishedOn())
                        .rating(userBook.getRating())
                        .review(userBook.getReview())
                        .readingStatus(userBook.getReadingStatus())
                        .build())
                .toList();
    }

}
