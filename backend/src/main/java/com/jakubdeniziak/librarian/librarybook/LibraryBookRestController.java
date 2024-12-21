package com.jakubdeniziak.librarian.librarybook;

import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class LibraryBookRestController implements LibraryBookController {

    private final LibraryBookService libraryBookService;
    private final LibraryBookMapper libraryBookMapper;

    @Override
    @GetMapping("/{library_id}/books/{book_id}")
    public LibraryBookResponse readBookInLibrary(@PathVariable("library_id") UUID libraryId, @PathVariable("book_id") UUID bookId) {
        LibraryBook libraryBook = libraryBookService.findOne(bookId, libraryId);
        return libraryBookMapper.map(libraryBook);
    }

    @Override
    @GetMapping("/{id}/books")
    public LibraryBooksResponse readAllBooksInLibrary(@PathVariable("id") UUID libraryId) {
        List<LibraryBook> libraryBooks = libraryBookService.findAllByLibraryId(libraryId);
        return libraryBookMapper.map(libraryBooks);
    }

    @Override
    @PutMapping("/{library_id}/books/{book_id}")
    public void addBookToLibrary(@PathVariable("book_id") UUID bookId, @PathVariable("library_id") UUID libraryId, @RequestBody LibraryBookRequest request) {
        int numberOfCopies = request.getNumberOfCopies();
        libraryBookService.save(bookId, libraryId, numberOfCopies);
    }

    @Override
    @DeleteMapping("/{library_id}/books/{book_id}")
    public void deleteBookFromLibrary(@PathVariable("book_id") UUID bookId, @PathVariable("library_id") UUID libraryId) {
        libraryBookService.delete(bookId, libraryId);
    }
}
