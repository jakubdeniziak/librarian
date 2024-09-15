package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.librarybook.LibraryBook;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "libraries")
public class Library {
    @Id
    private UUID id;
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy = "library", cascade = CascadeType.REMOVE)
    private Set<LibraryBook> libraryBooks;
}
