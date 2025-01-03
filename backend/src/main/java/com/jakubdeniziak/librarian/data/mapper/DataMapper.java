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
import com.jakubdeniziak.librarian.user.domain.User;
import com.jakubdeniziak.librarian.userbook.domain.UserBook;
import com.jakubdeniziak.librarian.userbook.domain.UserBookTuple;

import java.util.List;

public interface DataMapper {

    DataResponse map(List<Author> authors, List<Publisher> publishers, List<Library> libraries, List<User> users,
                     List<Book> books, List<LibraryBook> libraryBooks, List<UserBook> userBooks);
    List<Author> mapAuthors(DataRequest request);
    List<Publisher> mapPublishers(DataRequest request);
    List<Library> mapLibraries(DataRequest request);
    List<User> mapUsers(DataRequest request);
    List<BookTuple> mapBooks(DataRequest request);
    List<LibraryBookTuple> mapLibraryBooks(DataRequest request);
    List<UserBookTuple> mapUserBooks(DataRequest request);

}
