package com.jakubdeniziak.librarian.library.service;

import com.jakubdeniziak.librarian.exceptions.ResourceNotFoundException;
import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.mapper.LibraryMapper;
import com.jakubdeniziak.librarian.library.repository.LibraryJpaRepository;
import com.jakubdeniziak.librarian.security.domain.UserSecurityDetails;
import com.jakubdeniziak.librarian.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LibraryDefaultService implements LibraryService {

    private final LibraryJpaRepository repository;
    private final LibraryMapper mapper;
    private final UserService userService;

    @Override
    public void save(Library library) {
        UUID callerId = getCurrentUserId();
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
        return mapper.mapToDomain(repository.findAll());
    }

    @Override
    public Integer getCount() {
        return Math.toIntExact(repository.count());
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

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityDetails userDetails = (UserSecurityDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

}
