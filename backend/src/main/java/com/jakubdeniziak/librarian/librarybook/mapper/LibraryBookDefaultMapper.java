package com.jakubdeniziak.librarian.librarybook.mapper;

import com.jakubdeniziak.librarian.book.mapper.BookDomainToEntityMapper;
import com.jakubdeniziak.librarian.book.mapper.BookEntityToDomainMapper;
import com.jakubdeniziak.librarian.library.mapper.LibraryMapper;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookEntity;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookKey;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LibraryBookDefaultMapper implements LibraryBookMapper {

    private final LibraryMapper libraryMapper;
    private final BookDomainToEntityMapper bookDomainToEntityMapper;
    private final BookEntityToDomainMapper bookEntityToDomainMapper;

    @Override
    public LibraryBook map(LibraryBookRequest request) {
        return LibraryBook.builder()
                .numberOfCopies(request.getNumberOfCopies())
                .build();
    }

    @Override
    public LibraryBookEntity map(LibraryBook libraryBook) {
        return LibraryBookEntity.builder()
                .id(LibraryBookKey.builder()
                        .libraryId(libraryBook.getLibrary().getId())
                        .bookId(libraryBook.getBook().getId())
                        .build())
                .library(libraryMapper.map(libraryBook.getLibrary()))
                .book(bookDomainToEntityMapper.map(libraryBook.getBook()))
                .numberOfCopies(libraryBook.getNumberOfCopies())
                .build();
    }

    @Override
    public List<LibraryBookEntity> map(List<LibraryBook> libraryBooks) {
        return libraryBooks.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public LibraryBook mapToDomain(LibraryBookEntity libraryBook) {
        return LibraryBook.builder()
                .library(libraryMapper.mapToDomain(libraryBook.getLibrary()))
                .book(bookEntityToDomainMapper.mapToDomain(libraryBook.getBook()))
                .numberOfCopies(libraryBook.getNumberOfCopies())
                .build();
    }

    @Override
    public List<LibraryBook> mapToDomain(List<LibraryBookEntity> libraryBooks) {
        return libraryBooks.stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public LibraryBookResponse mapToResponse(LibraryBook libraryBook) {
        return LibraryBookResponse.builder()
                .libraryId(libraryBook.getLibrary().getId())
                .bookId(libraryBook.getBook().getId())
                .numberOfCopies(libraryBook.getNumberOfCopies())
                .build();
    }

    @Override
    public LibraryBooksResponse mapToResponse(List<LibraryBook> libraryBooks) {
        List<LibraryBooksResponse.LibraryBook> responseLibraryBooks = libraryBooks.stream()
                .map(libraryBook -> LibraryBooksResponse.LibraryBook.builder()
                        .libraryId(libraryBook.getLibrary().getId())
                        .bookId(libraryBook.getBook().getId())
                        .numberOfCopies(libraryBook.getNumberOfCopies())
                        .build())
                .toList();
        return LibraryBooksResponse.builder()
                .libraryBooks(responseLibraryBooks)
                .build();
    }

}
