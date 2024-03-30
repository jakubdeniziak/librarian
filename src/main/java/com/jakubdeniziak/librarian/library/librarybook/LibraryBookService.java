package com.jakubdeniziak.librarian.library.librarybook;

import com.jakubdeniziak.librarian.book.Book;
import com.jakubdeniziak.librarian.book.BookService;
import com.jakubdeniziak.librarian.library.Library;
import com.jakubdeniziak.librarian.library.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryBookService {
    private final LibraryBookRepository repository;
    private final LibraryService libraryService;
    private final BookService bookService;

    public LibraryBook findOne(UUID bookId, UUID libraryId) {
        return repository.findByBookIdAndLibraryId(bookId, libraryId);
    }

    public List<LibraryBook> findAllByLibraryId(UUID libraryId) {
        return repository.findAllByLibraryId(libraryId);
    }

    public void save(UUID bookId, UUID libraryId, int numberOfCopies) {
        Book book = bookService.findById(bookId);
        Library library = libraryService.findById(libraryId);
        LibraryBook libraryBook = LibraryBook.builder()
                .library(library)
                .book(book)
                .numberOfCopies(numberOfCopies)
                .build();

        repository.save(libraryBook);
    }

    @Transactional
    public void delete(UUID bookId, UUID libraryId) {
        repository.deleteByBookIdAndLibraryId(bookId, libraryId);
    }
}
