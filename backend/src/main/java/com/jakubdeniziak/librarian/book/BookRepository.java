package com.jakubdeniziak.librarian.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByAuthorId(UUID id);
    List<Book> findAllByPublisherId(UUID id);
}
