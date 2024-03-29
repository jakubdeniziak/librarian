package com.jakubdeniziak.librarian.author;

import com.jakubdeniziak.librarian.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Author {
    @Id
    private UUID id;
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> booksWritten;
}
