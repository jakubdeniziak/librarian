package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PublisherMapper {
    public PublisherResponse map(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .build();
    }

    public PublishersResponse map(List<Publisher> publishers) {
        return PublishersResponse.builder()
                .publishers(publishers.stream()
                        .map(publisher -> PublishersResponse.Publisher.builder()
                                .id(publisher.getId())
                                .websiteUrl(publisher.getWebsiteUrl())
                                .description(publisher.getDescription())
                                .build())
                        .toList())
                .build();
    }

    public Publisher map(UUID id, PublisherRequest request) {
        return Publisher.builder()
                .id(id)
                .websiteUrl(request.getWebsiteUrl())
                .description(request.getDescription())
                .build();
    }
}
