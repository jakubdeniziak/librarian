package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class PublishersResponse {

    @Value
    public static class Publisher {
        UUID id;
        String name;
    }

    List<Publisher> publishers;

}
