package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.author.Author;
import com.jakubdeniziak.librarian.publisher.Publisher;
import jakarta.persistence.*;
import lombok.*;

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
    private UUID id;
    private String isbn;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;
}
