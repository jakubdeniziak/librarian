package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class PublisherResponse {

    UUID id;
    String name;
    String websiteUrl;
    String description;

}
