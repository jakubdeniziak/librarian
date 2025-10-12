package com.jakubdeniziak.librarian.security.repository;

import com.jakubdeniziak.librarian.security.entity.UserSecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserSecurityRepository extends JpaRepository<UserSecurityEntity, UUID> {

    Optional<UserSecurityEntity> findByUsername(String username);

}
