package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PublisherResponse {

    UUID id;
    String name;
    String websiteUrl;
    String description;

}
