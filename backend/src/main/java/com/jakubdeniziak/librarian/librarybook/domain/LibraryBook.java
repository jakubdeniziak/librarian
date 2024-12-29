package com.jakubdeniziak.librarian.librarybook.domain;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.library.domain.Library;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibraryBook {

    private Library library;
    private Book book;
    private Integer numberOfCopies;

}
