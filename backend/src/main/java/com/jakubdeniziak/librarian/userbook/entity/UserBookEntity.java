package com.jakubdeniziak.librarian.userbook.entity;

import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "UserBook")
@Table(name = "user_books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserBookEntity {

    @Id
    private UUID id;

    private LocalDateTime startedOn;
    private LocalDateTime finishedOn;
    private Float rating;
    private String review;
    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;
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
        UserBookEntity userBookEntity = (UserBookEntity) o;
        return id != null && Objects.equals(id, userBookEntity.id);
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
