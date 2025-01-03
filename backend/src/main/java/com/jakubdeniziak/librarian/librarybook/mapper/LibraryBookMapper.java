package com.jakubdeniziak.librarian.librarybook.mapper;

import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookEntity;

import java.util.List;

public interface LibraryBookMapper {

    LibraryBook map(LibraryBookRequest request);
    LibraryBookEntity map(LibraryBook libraryBook);
    List<LibraryBookEntity> map(List<LibraryBook> libraryBooks);
    LibraryBook mapToDomain(LibraryBookEntity libraryBook);
    List<LibraryBook> mapToDomain(List<LibraryBookEntity> libraryBooks);
    LibraryBookResponse mapToResponse(LibraryBook libraryBook);
    LibraryBooksResponse mapToResponse(List<LibraryBook> libraryBooks);

}
