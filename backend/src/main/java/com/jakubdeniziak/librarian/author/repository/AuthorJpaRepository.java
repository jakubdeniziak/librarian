package com.jakubdeniziak.librarian.author.repository;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, UUID> {
}
