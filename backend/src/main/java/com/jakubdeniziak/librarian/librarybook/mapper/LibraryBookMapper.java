package com.jakubdeniziak.librarian.librarybook.mapper;

import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookEntity;

import java.util.List;
import java.util.UUID;

public interface LibraryBookMapper {

    LibraryBook map(UUID libraryId, UUID bookId, LibraryBookRequest request);
    LibraryBookEntity map(LibraryBook libraryBook);
    LibraryBook map(LibraryBookEntity libraryBook);
    List<LibraryBook> map(List<LibraryBookEntity> libraryBooks);
    LibraryBookResponse mapToResponse(LibraryBook libraryBook);
    LibraryBooksResponse mapToResponse(List<LibraryBook> libraryBooks);

}
