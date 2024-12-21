package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Deprecated
public class PublisherDeprecatedMapper {

    public PublisherEntity mapToEntity(Publisher publisher) {
        return PublisherEntity.builder().build();
    }

    public Optional<Publisher> mapToOptional(Optional<PublisherEntity> entity) {
        return Optional.of(Publisher.builder().build());
    }

    public List<Publisher> mapEntities(List<PublisherEntity> entities) {
        return List.of();
    }

    public PublisherResponse map(Publisher publisher) {
        return new PublisherResponse(null, null, null, null);
    }

    public PublishersResponse map(List<Publisher> publisherEntities) {
        return new PublishersResponse(List.of());
    }

    public Publisher map(UUID id, PublisherRequest request) {
        return Publisher.builder().build();
    }

}
