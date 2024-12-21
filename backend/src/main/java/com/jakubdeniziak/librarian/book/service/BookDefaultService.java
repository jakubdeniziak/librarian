package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.author.domain.Author;
import com.jakubdeniziak.librarian.author.service.AuthorDefaultService;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.entity.BookEntity;
import com.jakubdeniziak.librarian.book.mapper.BookDeprecatedMapper;
import com.jakubdeniziak.librarian.book.repository.BookJpaRepository;
import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.publisher.entity.PublisherEntity;
import com.jakubdeniziak.librarian.publisher.service.PublisherDefaultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookDefaultService implements BookService {

    private final BookJpaRepository repository;
    private final BookDeprecatedMapper mapper;
    private final AuthorDefaultService authorDefaultService;
    private final PublisherDefaultService publisherDefaultService;

    @Override
    public void save(Book book) {
        repository.save(bookEntity);
    }

    @Override
    public Optional<Book> find(UUID id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Book> findAllByAuthor(UUID id) {
        return repository.findAllByAuthorId(id);
    }

    @Override
    public List<Book> findAllByPublisher(UUID id) {
        return repository.findAllByPublisherId(id);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(UUID id, Book updated) {
        BookEntity bookEntity = findById(id);

        if (updated.getIsbn() != null) {
            bookEntity.setIsbn(updated.getIsbn());
        }
        if (updated.getTitle() != null) {
            bookEntity.setTitle(updated.getTitle());
        }
        if (updated.getAuthorEntity().getId() != null) {
            // TODO
            Author author = authorDefaultService.find(updated.getAuthorEntity().getId()).orElseThrow(ResourceNotFoundException::new);
            bookEntity.setAuthorEntity(null);
        }
        if (updated.getPublisher().getId() != null) {
            PublisherEntity publisherEntity = publisherDefaultService.findById(updated.getPublisher().getId());
            bookEntity.setPublisherEntity(publisherEntity);
        }

        save(bookEntity);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
