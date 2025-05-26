package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;

import java.util.UUID;

public interface PublisherRequestToDomainMapper {

    Publisher map(UUID id, PublisherRequest request);

}
