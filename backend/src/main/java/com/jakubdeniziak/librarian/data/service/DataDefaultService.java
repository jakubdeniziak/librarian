package com.jakubdeniziak.librarian.data.service;

import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.data.dto.DataRequest;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.data.mapper.DataMapper;
import com.jakubdeniziak.librarian.library.service.LibraryService;
import com.jakubdeniziak.librarian.librarybook.service.LibraryBookService;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import com.jakubdeniziak.librarian.user.service.UserService;
import com.jakubdeniziak.librarian.userbook.service.UserBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataDefaultService implements DataService {

    private final DataMapper mapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final LibraryService libraryService;
    private final UserService userService;
    private final BookService bookService;
    private final LibraryBookService libraryBookService;
    private final UserBookService userBookService;

    @Override
    public void saveAll(DataRequest request) {
        authorService.saveAll(mapper.mapAuthors(request));
        publisherService.saveAll(mapper.mapPublishers(request));
        libraryService.saveAll(mapper.mapLibraries(request));
        userService.saveAll(mapper.mapUsers(request));
        bookService.saveAll(mapper.mapBooks(request));
        libraryBookService.saveAll(mapper.mapLibraryBooks(request));
        userBookService.saveAll(mapper.mapUserBooks(request));
    }

    @Override
    public DataResponse findAll() {
        return mapper.map(authorService.findAll(), publisherService.findAll(), libraryService.findAll(), userService.findAll(),
                bookService.findAll(), libraryBookService.findAll(), userBookService.findAll());
    }

}
