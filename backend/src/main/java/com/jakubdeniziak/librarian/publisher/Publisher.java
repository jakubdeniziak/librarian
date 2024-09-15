package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.book.Book;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "publishers")
public class Publisher {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String name;
    private String websiteUrl;
    private String description;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> booksPublished;
}
