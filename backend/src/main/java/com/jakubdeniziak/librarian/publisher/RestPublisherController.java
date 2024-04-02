package com.jakubdeniziak.librarian.publisher;

import com.jakubdeniziak.librarian.book.Book;
import com.jakubdeniziak.librarian.book.BookMapper;
import com.jakubdeniziak.librarian.book.BookService;
import com.jakubdeniziak.librarian.book.dto.BooksResponse;
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
    private final PublisherService publisherService;
    private final BookService bookService;
    private final PublisherMapper publisherMapper;
    private final BookMapper bookMapper;

    @Override
    @GetMapping("/{id}")
    public PublisherResponse readPublisher(@PathVariable("id") UUID id) {
        Publisher publisher = publisherService.findById(id);
        return publisherMapper.map(publisher);
    }

    @Override
    @GetMapping
    public PublishersResponse readPublishers() {
        List<Publisher> publishers = publisherService.findAll();
        return publisherMapper.map(publishers);
    }

    @Override
    @GetMapping("/{id}/books")
    public BooksResponse readBooksByPublisher(@PathVariable("id") UUID publisherId) {
        List<Book> books = bookService.findAllByPublisherId(publisherId);
        return bookMapper.map(books);
    }

    @Override
    @PutMapping("/{id}")
    public void createPublisher(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        Publisher publisher = publisherMapper.map(id, request);
        publisherService.save(publisher);
    }

    @Override
    @PatchMapping("/{id}")
    public void updatePublisher(@PathVariable("id") UUID id, @RequestBody PublisherRequest request) {
        Publisher publisher = publisherMapper.map(id, request);
        publisherService.update(id, publisher);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deletePublisher(@PathVariable("id") UUID id) {
        publisherService.delete(id);
    }
}
