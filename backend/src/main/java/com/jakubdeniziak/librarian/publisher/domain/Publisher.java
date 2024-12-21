package com.jakubdeniziak.librarian.publisher.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Publisher {

    private UUID id;
    private String name;
    private String websiteUrl;
    private String description;

}
