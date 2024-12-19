package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import com.jakubdeniziak.librarian.publisher.Publisher;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String isbn;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity authorEntity;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
