package com.jakubdeniziak.librarian.book;

import com.jakubdeniziak.librarian.author.entity.AuthorEntity;
import com.jakubdeniziak.librarian.author.service.DefaultAuthorService;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.Publisher;
import com.jakubdeniziak.librarian.publisher.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final DefaultAuthorService defaultAuthorService;
    private final PublisherService publisherService;

    public Book findById(UUID id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findAllByAuthorId(UUID id) {
        return repository.findAllByAuthorId(id);
    }

    public List<Book> findAllByPublisherId(UUID id) {
        return repository.findAllByPublisherId(id);
    }

    public void save(Book book) {
        repository.save(book);
    }

    public void update(UUID id, Book updatedBook) {
        Book book = findById(id);

        if (updatedBook.getIsbn() != null) {
            book.setIsbn(updatedBook.getIsbn());
        }
        if (updatedBook.getTitle() != null) {
            book.setTitle(updatedBook.getTitle());
        }
        if (updatedBook.getAuthorEntity().getId() != null) {
            AuthorEntity authorEntity = defaultAuthorService.find(updatedBook.getAuthorEntity().getId()).orElseThrow(ResourceNotFoundException::new);
            book.setAuthorEntity(authorEntity);
        }
        if (updatedBook.getPublisher().getId() != null) {
            Publisher publisher = publisherService.findById(updatedBook.getPublisher().getId());
            book.setPublisher(publisher);
        }

        save(book);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
