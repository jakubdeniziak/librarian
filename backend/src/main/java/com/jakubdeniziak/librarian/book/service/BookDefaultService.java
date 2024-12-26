package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.mapper.BookMapper;
import com.jakubdeniziak.librarian.book.repository.BookJpaRepository;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookDefaultService implements BookService {

    private final BookJpaRepository repository;
    private final BookMapper mapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Override
    public void save(Book book, UUID authorId, UUID publisherId) {
        book.setAuthor(authorService.find(authorId));
        book.setPublisher(publisherService.find(publisherId));
        repository.save(mapper.map(book));
    }

    @Override
    public Book find(UUID id) {
        return mapper.map(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Book> findAllByAuthor(UUID id) {
        return mapper.map(repository.findAllByAuthorId(id));
    }

    @Override
    public List<Book> findAllByPublisher(UUID id) {
        return mapper.map(repository.findAllByPublisherId(id));
    }

    @Override
    public List<Book> findAll() {
        return mapper.map(repository.findAll());
    }

    @Override
    public void update(UUID id, Book updated, UUID authorId, UUID publisherId) {
        Book book = find(id);
        if (updated.getIsbn() != null) {
            book.setIsbn(updated.getIsbn());
        }
        if (updated.getTitle() != null) {
            book.setTitle(updated.getTitle());
        }
        if (updated.getDescription() != null) {
            book.setDescription(updated.getDescription());
        }
        if (updated.getAuthor() != null) {
            book.setAuthor(authorService.find(authorId));
        }
        if (updated.getPublisher() != null) {
            book.setPublisher(publisherService.find(publisherId));
        }
        save(book, authorId, publisherId);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
