package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class PublishersResponse {
    private List<Publisher> publishers;

    @Builder
    @Getter
    public static class Publisher {
        private UUID id;
        private String websiteUrl;
        private String description;
    }
}
