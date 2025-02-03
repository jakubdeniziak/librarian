package com.jakubdeniziak.librarian.publisher.controller;

import com.jakubdeniziak.librarian.publisher.mapper.PublisherDefaultMapper;
import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/publishers")
@AllArgsConstructor
public class PublisherRestController implements PublisherController {
    
    private final PublisherService service;
    private final PublisherDefaultMapper mapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @RequestBody PublisherRequest request) {
        service.save(mapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public PublisherResponse read(@PathVariable UUID id) {
        return mapper.mapToResponse(service.find(id));
    }

    @Override
    @GetMapping
    public PublishersResponse readAll() {
        return mapper.mapToResponse(service.findAll());
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody PublisherRequest request) {
        service.update(id, mapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
    
}
