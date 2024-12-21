package com.jakubdeniziak.librarian.librarybook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    LibraryBook findByBookIdAndLibraryId(UUID bookId, UUID libraryId);
    List<LibraryBook> findAllByLibraryId(UUID libraryId);

    void deleteByBookIdAndLibraryId(UUID bookId, UUID libraryId);
}
