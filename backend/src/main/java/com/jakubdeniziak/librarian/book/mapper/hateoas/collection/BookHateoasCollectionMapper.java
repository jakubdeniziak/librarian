package com.jakubdeniziak.librarian.book.mapper.hateoas.collection;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface BookHateoasCollectionMapper {

    CollectionModel<EntityModel<BookResponse>> mapToPartialResponse(List<Book> books);

}
