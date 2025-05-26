package com.jakubdeniziak.librarian.publisher.dto.response.publisher;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PublisherDefaultResponse implements PublisherResponse {

    UUID id;
    String name;
    String websiteUrl;
    String description;

}
