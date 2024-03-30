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
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Library library;
    @ManyToOne
    private Book book;
    private int numberOfCopies;
}
