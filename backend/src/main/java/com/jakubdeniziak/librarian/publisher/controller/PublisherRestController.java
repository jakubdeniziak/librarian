package com.jakubdeniziak.librarian.publisher.controller;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.domain.Publisher;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherDeprecatedMapper;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherRestController implements PublisherController {
    
    private final PublisherService service;
    private final PublisherDeprecatedMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public PublisherResponse read(@PathVariable("id") UUID id) {
        Publisher publisher = service.find(id).orElseThrow(ResourceNotFoundException::new);
        return mapper.map(publisher);
    }

    @Override
    @GetMapping
    public PublishersResponse readAll() {
        return mapper.map(service.findAll());
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        service.update(id, mapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
    
}
