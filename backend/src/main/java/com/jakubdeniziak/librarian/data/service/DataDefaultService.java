package com.jakubdeniziak.librarian.data.service;

import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.data.dto.DataResponse;
import com.jakubdeniziak.librarian.data.mapper.DataMapper;
import com.jakubdeniziak.librarian.library.service.LibraryService;
import com.jakubdeniziak.librarian.librarybook.service.LibraryBookService;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DataDefaultService implements DataService {

    private final DataMapper mapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private final LibraryBookService libraryBookService;

    @Override
    public DataResponse findAll() {
        return mapper.map(authorService.findAll(), publisherService.findAll(), libraryService.findAll(), bookService.findAll(), libraryBookService.findAll());
    }

}
