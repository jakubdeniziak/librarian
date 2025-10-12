package com.jakubdeniziak.librarian.library.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import com.jakubdeniziak.librarian.library.mapper.LibraryMapper;
import com.jakubdeniziak.librarian.library.repository.LibraryJpaRepository;
import com.jakubdeniziak.librarian.security.service.CallerService;
import com.jakubdeniziak.librarian.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryDefaultService implements LibraryService {

    private final LibraryJpaRepository repository;
    private final LibraryMapper mapper;
    private final UserService userService;
    private final CallerService callerService;

    @Override
    public void save(Library library) {
        UUID callerId = callerService.getCallerId();
        library.setUser(userService.find(callerId));
        repository.save(mapper.map(library));
    }

    @Override
    public void saveAll(List<Library> libraries) {
        repository.saveAll(mapper.map(libraries));
    }

    @Override
    public Library find(UUID id) {
        return mapper.mapToDomain(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Library> findAll() {
        List<LibraryEntity> libraries;
        if (callerService.isCallerAdmin()) {
            libraries = repository.findAll();
        } else {
            UUID callerId = callerService.getCallerId();
            libraries = repository.findAllByUserId(callerId);
        }
        return mapper.mapToDomain(libraries);
    }

    @Override
    public Integer getCount() {
        long count;
        if (callerService.isCallerAdmin()) {
            count = repository.count();
        } else {
            UUID callerId = callerService.getCallerId();
            count = repository.countAllByUserId(callerId);
        }
        return Math.toIntExact(count);
    }

    @Override
    public UUID getOwnerId(UUID libraryId) {
        return find(libraryId).getUser().getId();
    }

    @Override
    public void update(UUID id, Library updated) {
        Library library = find(id);
        if (updated.getName() != null) {
            library.setName(updated.getName());
        }
        if (updated.getAddress() != null) {
            library.setAddress(updated.getAddress());
        }
        if (updated.getDescription() != null) {
            library.setDescription(updated.getDescription());
        }
        save(library);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
