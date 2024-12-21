package com.jakubdeniziak.librarian.author.entity;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity(name = "Author")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookEntity> booksWritten;
}
