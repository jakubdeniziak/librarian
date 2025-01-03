package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PublisherDefaultMapper implements PublisherMapper {

    @Override
    public Publisher map(UUID id, PublisherRequest request) {
        return Publisher.builder()
                .id(id)
                .name(request.getName())
                .websiteUrl(request.getWebsiteUrl())
                .description(request.getDescription())
                .build();
    }

    @Override
    public PublisherEntity map(Publisher publisher) {
        return PublisherEntity.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .build();
    }

    @Override
    public List<PublisherEntity> map(List<Publisher> publishers) {
        return publishers.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public Publisher mapToDomain(PublisherEntity publisher) {
        return Publisher.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .build();
    }

    @Override
    public List<Publisher> mapToDomain(List<PublisherEntity> publishers) {
        return publishers.stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public PublisherResponse mapToResponse(Publisher publisher) {
        return PublisherResponse.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .websiteUrl(publisher.getWebsiteUrl())
                .description(publisher.getDescription())
                .build();
    }

    @Override
    public PublishersResponse mapToResponse(List<Publisher> publishers) {
        List<PublishersResponse.Publisher> responsePublishers = publishers.stream()
                .map(publisher -> PublishersResponse.Publisher.builder()
                        .id(publisher.getId())
                        .name(publisher.getName())
                        .build())
                .toList();
        return PublishersResponse.builder()
                .publishers(responsePublishers)
                .build();
    }

}
