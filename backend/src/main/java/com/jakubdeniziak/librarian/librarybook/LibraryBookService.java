package com.jakubdeniziak.librarian.librarybook;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.book.service.BookDefaultService;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import com.jakubdeniziak.librarian.library.service.LibraryDefaultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryBookService {
    private final LibraryBookRepository repository;
    private final LibraryDefaultService libraryDefaultService;
    private final BookDefaultService bookDefaultService;

    public LibraryBook findOne(UUID bookId, UUID libraryId) {
        return repository.findByBookIdAndLibraryId(bookId, libraryId);
    }

    public List<LibraryBook> findAllByLibraryId(UUID libraryId) {
        return repository.findAllByLibraryId(libraryId);
    }

    public void save(UUID bookId, UUID libraryId, int numberOfCopies) {
        BookEntity bookEntity = bookDefaultService.findById(bookId);
        LibraryEntity libraryEntity = libraryDefaultService.findById(libraryId);
        LibraryBook libraryBook = LibraryBook.builder()
                .libraryEntity(libraryEntity)
                .bookEntity(bookEntity)
                .numberOfCopies(numberOfCopies)
                .build();

        repository.save(libraryBook);
    }

    @Transactional
    public void delete(UUID bookId, UUID libraryId) {
        repository.deleteByBookIdAndLibraryId(bookId, libraryId);
    }
}
