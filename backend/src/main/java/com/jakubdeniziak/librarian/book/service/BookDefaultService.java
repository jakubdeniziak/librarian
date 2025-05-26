package com.jakubdeniziak.librarian.book.service;

import com.jakubdeniziak.librarian.author.service.AuthorService;
import com.jakubdeniziak.librarian.book.domain.Book;
import com.jakubdeniziak.librarian.book.domain.BookTuple;
import com.jakubdeniziak.librarian.book.mapper.BookDomainToEntityMapper;
import com.jakubdeniziak.librarian.book.mapper.BookEntityToDomainMapper;
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
    private final BookDomainToEntityMapper domainToEntityMapper;
    private final BookEntityToDomainMapper entityToDomainMapper;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Override
    public void save(Book book, UUID authorId, UUID publisherId) {
        Book initializedBook = getInitializedBook(book, authorId, publisherId);
        repository.save(domainToEntityMapper.map(initializedBook));
    }

    @Override
    public void saveAll(List<BookTuple> bookTuples) {
        List<Book> initializedBooks = bookTuples.stream()
                .map(bookTuple -> getInitializedBook(bookTuple.getBook(), bookTuple.getAuthorId(), bookTuple.getPublisherId()))
                .toList();
        repository.saveAll(domainToEntityMapper.map(initializedBooks));
    }

    @Override
    public Book find(UUID id) {
        return entityToDomainMapper.mapToDomain(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Book> findAllByAuthor(UUID id) {
        return entityToDomainMapper.mapToDomain(repository.findAllByAuthorId(id));
    }

    @Override
    public List<Book> findAllByPublisher(UUID id) {
        return entityToDomainMapper.mapToDomain(repository.findAllByPublisherId(id));
    }

    @Override
    public List<Book> findAll() {
        return entityToDomainMapper.mapToDomain(repository.findAll());
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(repository.count());
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

    private Book getInitializedBook(Book book, UUID authorId, UUID publisherId) {
        book.setAuthor(authorService.find(authorId));
        book.setPublisher(publisherService.find(publisherId));
        return book;
    }

}
