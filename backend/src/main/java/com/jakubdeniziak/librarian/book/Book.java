package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.author.Author;
import com.jakubdeniziak.librarian.publisher.Publisher;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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
