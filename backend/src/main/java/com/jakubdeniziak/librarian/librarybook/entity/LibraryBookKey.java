package com.jakubdeniziak.librarian.librarybook.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBookKey implements Serializable {

    private UUID libraryId;
    private UUID bookId;

}
