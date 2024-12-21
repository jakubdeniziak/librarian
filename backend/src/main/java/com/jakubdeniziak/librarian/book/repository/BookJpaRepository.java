package com.jakubdeniziak.librarian.book.repository;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {

    List<BookEntity> findAllByAuthorId(UUID id);
    List<BookEntity> findAllByPublisherId(UUID id);

}
