package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;

import java.util.List;

public interface PublisherEntityToDomainMapper {

    Publisher mapToDomain(PublisherEntity publisher);
    List<Publisher> mapToDomain(List<PublisherEntity> publishers);

}
