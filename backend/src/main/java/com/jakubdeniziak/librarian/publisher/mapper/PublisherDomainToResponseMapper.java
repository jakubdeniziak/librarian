package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.response.publishers.PublishersResponse;

import java.util.List;

public interface PublisherDomainToResponseMapper {

    PublisherResponse mapToResponse(Publisher publisher);
    PublishersResponse mapToResponse(List<Publisher> publishers);

}
