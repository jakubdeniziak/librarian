package com.jakubdeniziak.librarian.userbook.repository;

import com.jakubdeniziak.librarian.userbook.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBookJpaRepository extends JpaRepository<UserBookEntity, UUID> {

    List<UserBookEntity> findAllByUserId(UUID id);

}
