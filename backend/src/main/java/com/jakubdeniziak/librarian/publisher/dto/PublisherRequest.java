package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PublisherRequest {

    String name;
    String websiteUrl;
    String description;

}
