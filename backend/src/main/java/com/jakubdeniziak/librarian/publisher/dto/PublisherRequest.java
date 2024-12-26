package com.jakubdeniziak.librarian.publisher.dto;

import lombok.Value;

@Value
public class PublisherRequest {

    String name;
    String websiteUrl;
    String description;

}
