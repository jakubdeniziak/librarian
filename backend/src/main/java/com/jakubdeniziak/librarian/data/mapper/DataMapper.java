package com.jakubdeniziak.librarian.data.mapper;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.domain.BookTuple;
import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBookTuple;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;

import java.util.List;

public interface DataMapper {

    DataResponse map(List<Author> authors, List<Publisher> publishers, List<Library> libraries, List<Book> books, List<LibraryBook> libraryBooks);
    List<Author> mapAuthors(DataRequest request);
    List<Publisher> mapPublishers(DataRequest request);
    List<Library> mapLibraries(DataRequest request);
    List<BookTuple> mapBooks(DataRequest request);
    List<LibraryBookTuple> mapLibraryBooks(DataRequest request);

}
