package com.jakubdeniziak.librarian.librarybook;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
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
    private LibraryEntity libraryEntity;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity bookEntity;
    private int numberOfCopies;
}
