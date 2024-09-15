package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.book.Book;
import jakarta.persistence.*;
import lombok.*;

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
    private UUID id;
    private String name;
    private String websiteUrl;
    private String description;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> booksPublished;
}
