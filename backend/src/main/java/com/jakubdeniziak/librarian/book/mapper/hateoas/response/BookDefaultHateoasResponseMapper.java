package com.jakubdeniziak.librarian.book.mapper.hateoas.response;

import com.jakubdeniziak.librarian.author.mapper.hateoas.AuthorHateoasMapper;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.dto.response.book.BookHateoasResponse;
import com.jakubdeniziak.librarian.book.dto.response.book.BookResponse;
import com.jakubdeniziak.librarian.publisher.mapper.hateoas.PublisherHateoasMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookDefaultHateoasResponseMapper implements BookHateoasResponseMapper {

    private final AuthorHateoasMapper authorHateoasMapper;
    private final PublisherHateoasMapper publisherHateoasMapper;

    @Override
    public BookResponse mapToResponse(Book book) {
        return BookHateoasResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .description(book.getDescription())
                .format(book.getFormat())
                .authorId(book.getAuthor().getId())
                .publisherId(book.getPublisher().getId())
                .author(authorHateoasMapper.mapToPartialResponse(book.getAuthor()))
                .publisher(publisherHateoasMapper.mapToPartialResponse(book.getPublisher()))
                .build();
    }

}
