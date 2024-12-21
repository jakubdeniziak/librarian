package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import com.jakubdeniziak.librarian.librarybook.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.LibraryBookMapper;
import com.jakubdeniziak.librarian.librarybook.LibraryBookService;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookRequest;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBookResponse;
import com.jakubdeniziak.librarian.librarybook.dto.LibraryBooksResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/libraries")
@AllArgsConstructor
public class RestLibraryController implements LibraryController {
    private final LibraryService libraryService;
    private final LibraryBookService libraryBookService;
    private final LibraryMapper libraryMapper;
    private final LibraryBookMapper libraryBookMapper;


    @Override
    @GetMapping("/{id}")
    public LibraryResponse readLibrary(@PathVariable("id") UUID id) {
        Library library = libraryService.findById(id);
        return libraryMapper.map(library);
    }

    @Override
    @GetMapping
    public LibrariesResponse readLibraries() {
        List<Library> libraries = libraryService.findAll();
        return libraryMapper.map(libraries);
    }

    @Override
    @PutMapping("/{id}")
    public void createLibrary(@PathVariable("id") UUID id, @RequestBody LibraryRequest request) {
        Library library = libraryMapper.map(id, request);
        libraryService.save(library);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateLibrary(@PathVariable UUID id, @RequestBody LibraryRequest request) {
        Library library = libraryMapper.map(id, request);
        libraryService.update(id, library);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable UUID id) {
        libraryService.delete(id);
    }

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
