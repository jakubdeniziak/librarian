package com.jakubdeniziak.librarian.librarybook.entity;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

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

    @PositiveOrZero
    private Integer numberOfCopies;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "library_id")
    private LibraryEntity library;
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        LibraryBookEntity that = (LibraryBookEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

}
