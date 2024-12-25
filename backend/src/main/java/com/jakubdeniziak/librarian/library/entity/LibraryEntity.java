package com.jakubdeniziak.librarian.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity(name = "Library")
@Table(name = "libraries")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LibraryEntity {

    @Id
    private UUID id;

    private String name;
    private String address;
    private String description;

}
