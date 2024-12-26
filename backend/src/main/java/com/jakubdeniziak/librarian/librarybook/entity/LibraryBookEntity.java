package com.jakubdeniziak.librarian.librarybook.entity;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "LibraryBook")
@Table(name = "library_books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LibraryBookEntity {

    @EmbeddedId
    private LibraryBookKey id;

    private Integer numberOfCopies;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "library_id")
    private LibraryEntity library;
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookEntity entity;

}
