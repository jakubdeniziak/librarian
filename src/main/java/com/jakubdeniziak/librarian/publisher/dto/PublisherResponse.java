package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class PublisherResponse {
    private UUID id;
    private String websiteUrl;
    private String description;
}
