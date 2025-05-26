package com.jakubdeniziak.librarian.publisher.controller;

import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.service.BookService;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.response.publishers.PublishersResponse;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherRequestToDomainMapper;
import com.jakubdeniziak.librarian.publisher.mapper.hateoas.PublisherHateoasMapper;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v2/publishers")
@AllArgsConstructor
public class PublisherHateoasController implements PublisherController {

    private final PublisherService publisherService;
    private final BookService bookService;
    private final PublisherRequestToDomainMapper requestToDomainMapper;
    private final PublisherHateoasMapper hateoasMapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @Valid @RequestBody PublisherRequest request) {
        publisherService.save(requestToDomainMapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public PublisherResponse read(@PathVariable UUID id) {
        Publisher publisher = publisherService.find(id);
        List<Book> publisherBooks = bookService.findAllByPublisher(id);
        return hateoasMapper.mapToResponse(publisher, publisherBooks);
    }

    @Override
    @GetMapping
    public PublishersResponse readAll() {
        // TODO: implement
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @GetMapping("/count")
    public Integer getCount() {
        return publisherService.getCount();
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody PublisherRequest request) {
        publisherService.update(id, requestToDomainMapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        publisherService.delete(id);
    }

}
