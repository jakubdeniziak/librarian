package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.librarybook.LibraryBook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;
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
    private String description;
    @OneToMany(mappedBy = "library", cascade = CascadeType.REMOVE)
    private Set<LibraryBook> libraryBooks;
}
