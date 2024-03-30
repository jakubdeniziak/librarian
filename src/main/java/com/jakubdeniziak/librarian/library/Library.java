package com.jakubdeniziak.librarian.library;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Library {
    @Id
    private UUID id;
    private String name;
    private String address;
}
