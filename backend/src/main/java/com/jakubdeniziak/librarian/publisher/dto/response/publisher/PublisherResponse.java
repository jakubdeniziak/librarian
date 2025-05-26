package com.jakubdeniziak.librarian.publisher.dto.response.publisher;

import java.util.UUID;

public interface PublisherResponse {

    UUID getId();
    String getName();
    String getWebsiteUrl();
    String getDescription();

}
