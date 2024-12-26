package com.jakubdeniziak.librarian.librarybook.mapper;

import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LibraryBookDefaultMapper implements LibraryBookMapper {

    @Override
    public LibraryBook map(UUID libraryId, UUID bookId, LibraryBookRequest request) {
        return null;
    }

    @Override
    public LibraryBookEntity map(LibraryBook libraryBook) {
        return null;
    }

    @Override
    public LibraryBook map(LibraryBookEntity libraryBook) {
        return null;
    }

    @Override
    public List<LibraryBook> map(List<LibraryBookEntity> libraryBooks) {
        return null;
    }

    @Override
    public LibraryBookResponse mapToResponse(LibraryBook libraryBook) {
        return null;
    }

    @Override
    public LibraryBooksResponse mapToResponse(List<LibraryBook> libraryBooks) {
        return null;
    }

}
