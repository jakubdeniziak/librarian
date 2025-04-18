package com.jakubdeniziak.librarian.publisher.mapper;

import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;

import java.util.List;

public interface PublisherDomainToEntityMapper {

    PublisherEntity map(Publisher publisher);
    List<PublisherEntity> map(List<Publisher> publishers);

}
