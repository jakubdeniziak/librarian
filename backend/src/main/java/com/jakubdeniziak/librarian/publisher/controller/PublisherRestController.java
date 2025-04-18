package com.jakubdeniziak.librarian.publisher.controller;

import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.response.publisher.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.response.publishers.PublishersResponse;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherDomainToResponseMapper;
import com.jakubdeniziak.librarian.publisher.mapper.PublisherRequestToDomainMapper;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/publishers")
@AllArgsConstructor
public class PublisherRestController implements PublisherController {
    
    private final PublisherService service;
    private final PublisherDomainToResponseMapper domainToResponseMapper;
    private final PublisherRequestToDomainMapper requestToDomainMapper;

    @Override
    @PutMapping("/{id}")
    public void create(@PathVariable UUID id, @Valid @RequestBody PublisherRequest request) {
        service.save(requestToDomainMapper.map(id, request));
    }

    @Override
    @GetMapping("/{id}")
    public PublisherResponse read(@PathVariable UUID id) {
        return domainToResponseMapper.mapToResponse(service.find(id));
    }

    @Override
    @GetMapping
    public PublishersResponse readAll() {
        return domainToResponseMapper.mapToResponse(service.findAll());
    }

    @Override
    @PatchMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody PublisherRequest request) {
        service.update(id, requestToDomainMapper.map(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
    
}
