package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;

import java.util.List;
import java.util.UUID;

public interface PublisherMapper {

    Publisher map(UUID id, PublisherRequest request);
    PublisherEntity map(Publisher publisher);
    List<PublisherEntity> map(List<Publisher> publishers);
    Publisher mapToDomain(PublisherEntity publisher);
    List<Publisher> mapToDomain(List<PublisherEntity> publishers);
    PublisherResponse mapToResponse(Publisher publisher);
    PublishersResponse mapToResponse(List<Publisher> publishers);

}
