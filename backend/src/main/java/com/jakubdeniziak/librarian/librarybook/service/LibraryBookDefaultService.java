package com.jakubdeniziak.librarian.librarybook.service;

import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.service.LibraryService;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBookTuple;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookKey;
import com.jakubdeniziak.librarian.librarybook.mapper.LibraryBookMapper;
import com.jakubdeniziak.librarian.librarybook.repository.LibraryBookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryBookDefaultService implements LibraryBookService {

    private final LibraryBookJpaRepository repository;
    private final LibraryBookMapper mapper;
    private final LibraryService libraryService;
    private final BookService bookService;

    @Override
    public void save(LibraryBook libraryBook, UUID libraryId, UUID bookId) {
        LibraryBook initializedLibraryBook = getInitializedLibraryBook(libraryBook, libraryId, bookId);
        repository.save(mapper.map(initializedLibraryBook));
    }

    @Override
    public void saveAll(List<LibraryBookTuple> libraryBookTuples) {
        List<LibraryBook> initializedLibraryBooks = libraryBookTuples.stream()
                .map(libraryBookTuple -> getInitializedLibraryBook(libraryBookTuple.getLibraryBook(),
                        libraryBookTuple.getLibraryId(),
                        libraryBookTuple.getBookId()))
                .toList();
        repository.saveAll(mapper.map(initializedLibraryBooks));
    }

    @Override
    public LibraryBook find(UUID libraryId, UUID bookId) {
        return mapper.mapToDomain(repository.findById(createKey(libraryId, bookId))
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<LibraryBook> findAllByLibrary(UUID libraryId) {
        return mapper.mapToDomain(repository.findAllByLibrary_Id(libraryId));
    }

    @Override
    public List<LibraryBook> findAll() {
        return mapper.mapToDomain(repository.findAll());
    }

    @Override
    public void update(UUID libraryId, UUID bookId, LibraryBook updated) {
        LibraryBook libraryBook = find(libraryId, bookId);
        if (updated.getNumberOfCopies() != null) {
            libraryBook.setNumberOfCopies(updated.getNumberOfCopies());
        }
        if (updated.getLibrary() != null) {
            libraryBook.setLibrary(updated.getLibrary());
        }
        if (updated.getBook() != null) {
            libraryBook.setBook(updated.getBook());
        }
        save(libraryBook, libraryId, bookId);
    }

    @Override
    public void delete(UUID libraryId, UUID bookId) {
        repository.deleteById(createKey(libraryId, bookId));
    }

    private LibraryBookKey createKey(UUID libraryId, UUID bookId) {
        return new LibraryBookKey(libraryId, bookId);
    }

    private LibraryBook getInitializedLibraryBook(LibraryBook libraryBook, UUID libraryId, UUID bookId) {
        libraryBook.setLibrary(libraryService.find(libraryId));
        libraryBook.setBook(bookService.find(bookId));
        return libraryBook;
    }

}
