package com.jakubdeniziak.librarian.library.entity;

import com.jakubdeniziak.librarian.librarybook.LibraryBook;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Library")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "libraries")
public class LibraryEntity {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy = "library", cascade = CascadeType.REMOVE)
    private Set<LibraryBook> libraryBooks;

}
