package com.jakubdeniziak.librarian.publisher.mapper.hateoas;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface PublisherHateoasMapper {

    EntityModel<PublisherResponse> mapToPartialResponse(Publisher publisher);
    PublisherResponse mapToResponse(Publisher publisher, List<Book> books);

}
