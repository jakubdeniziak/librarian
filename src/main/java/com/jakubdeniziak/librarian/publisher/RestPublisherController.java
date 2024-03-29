package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.publisher.dto.PublisherRequest;
import com.jakubdeniziak.librarian.publisher.dto.PublisherResponse;
import com.jakubdeniziak.librarian.publisher.dto.PublishersResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/publishers")
@AllArgsConstructor
public class RestPublisherController implements PublisherController {
    private final PublisherService service;
    private final PublisherMapper mapper;

    @Override
    @GetMapping("/{id}")
    public PublisherResponse readPublisher(@PathVariable("id") UUID id) {
        Publisher publisher = service.findById(id);
        return mapper.map(publisher);
    }

    @Override
    @GetMapping
    public PublishersResponse readPublishers() {
        List<Publisher> publishers = service.findAll();
        return mapper.map(publishers);
    }

    @Override
    @PutMapping("/{id}")
    public void createPublisher(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        Publisher publisher = mapper.map(id, request);
        service.save(publisher);
    }

    @Override
    @PatchMapping("/{id}")
    public void updatePublisher(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        Publisher publisher = mapper.map(id, request);
        service.update(id, publisher);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
