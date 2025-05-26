package com.jakubdeniziak.librarian.library.repository;

import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibraryJpaRepository extends JpaRepository<LibraryEntity, UUID> {
}
