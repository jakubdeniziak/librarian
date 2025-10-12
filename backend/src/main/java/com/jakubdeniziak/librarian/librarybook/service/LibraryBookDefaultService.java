package com.jakubdeniziak.librarian.librarybook.service;

import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.service.LibraryService;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBook;
import com.jakubdeniziak.librarian.librarybook.domain.LibraryBookTuple;
import com.jakubdeniziak.librarian.librarybook.entity.LibraryBookKey;
import com.jakubdeniziak.librarian.librarybook.mapper.LibraryBookMapper;
import com.jakubdeniziak.librarian.librarybook.repository.LibraryBookJpaRepository;
import com.jakubdeniziak.librarian.security.service.CallerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final CallerService callerService;

    @Override
    public void save(LibraryBook libraryBook, UUID libraryId, UUID bookId) {
        checkAuthorization(libraryId);
        LibraryBook initializedLibraryBook = getInitializedLibraryBook(libraryBook, libraryId, bookId);
        repository.save(mapper.map(initializedLibraryBook));
    }

    @Override
    public void saveAll(List<LibraryBookTuple> libraryBookTuples) {
        libraryBookTuples.forEach(libraryBookTuple ->
                checkAuthorization(libraryBookTuple.getLibraryId())
        );
        List<LibraryBook> initializedLibraryBooks = libraryBookTuples.stream()
                .map(libraryBookTuple -> getInitializedLibraryBook(libraryBookTuple.getLibraryBook(),
                        libraryBookTuple.getLibraryId(),
                        libraryBookTuple.getBookId()))
                .toList();
        repository.saveAll(mapper.map(initializedLibraryBooks));
    }

    @Override
    public LibraryBook find(UUID libraryId, UUID bookId) {
        checkAuthorization(libraryId);
        return mapper.mapToDomain(repository.findById(createKey(libraryId, bookId))
                .orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<LibraryBook> findAllByLibrary(UUID libraryId) {
        checkAuthorization(libraryId);
        return mapper.mapToDomain(repository.findAllByLibrary_Id(libraryId));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<LibraryBook> findAll() {
        return mapper.mapToDomain(repository.findAll());
    }

    @Override
    public void update(UUID libraryId, UUID bookId, LibraryBook updated) {
        checkAuthorization(libraryId);
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
        checkAuthorization(libraryId);
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

    private void checkAuthorization(UUID libraryId) {
        if (!isUserAuthorized(libraryId)) {
            throw new AccessDeniedException("You are not authorized to delete this library");
        }
    }

    private boolean isUserAuthorized(UUID libraryId) {
        if (callerService.isCallerAdmin()) {
            return true;
        }
        return libraryService.getOwnerId(libraryId).equals(callerService.getCallerId());
    }

}
