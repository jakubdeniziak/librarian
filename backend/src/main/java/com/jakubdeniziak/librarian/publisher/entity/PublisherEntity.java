package com.jakubdeniziak.librarian.publisher.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity(name = "Publisher")
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PublisherEntity {

    @Id
    private UUID id;

    private String name;
    private String websiteUrl;
    private String description;

}
