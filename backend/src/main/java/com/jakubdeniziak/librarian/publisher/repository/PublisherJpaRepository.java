package com.jakubdeniziak.librarian.publisher.repository;

import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherJpaRepository extends JpaRepository<PublisherEntity, UUID> {
}
