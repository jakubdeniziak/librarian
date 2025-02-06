package com.jakubdeniziak.librarian.publisher.dto.response.publishers;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class PublishersResponse {

    @Value
    @Builder
    public static class Publisher {

        UUID id;
        String name;

    }

    List<Publisher> publishers;

}
