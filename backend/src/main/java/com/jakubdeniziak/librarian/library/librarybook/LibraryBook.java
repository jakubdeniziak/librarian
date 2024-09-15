package com.jakubdeniziak.librarian.library.librarybook;

import com.jakubdeniziak.librarian.book.Book;
import com.jakubdeniziak.librarian.library.Library;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "library_books")
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int numberOfCopies;
}
