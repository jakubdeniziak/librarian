package com.jakubdeniziak.librarian.publisher;

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
public class Publisher {
    @Id
    private UUID id;
    private String websiteUrl;
    private String description;
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.REMOVE)
    private List<Book> booksPublished;
}
