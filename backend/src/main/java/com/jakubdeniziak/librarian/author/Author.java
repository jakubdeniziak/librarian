package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Author {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> booksWritten;
}
