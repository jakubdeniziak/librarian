package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/libraries")
@AllArgsConstructor
public class RestLibraryController implements LibraryController {
    private final LibraryService service;
    private final LibraryMapper mapper;


    @Override
    @GetMapping("/{id}")
    public LibraryResponse readLibrary(@PathVariable("id") UUID id) {
        Library library = service.findById(id);
        return mapper.map(library);
    }

    @Override
    @GetMapping
    public LibrariesResponse readLibraries() {
        List<Library> libraries = service.findAll();
        return mapper.map(libraries);
    }

    @Override
    @PutMapping("/{id}")
    public void createLibrary(@PathVariable("id") UUID id, @RequestBody LibraryRequest request) {
        Library library = mapper.map(id, request);
        service.save(library);
    }

    @Override
    @PatchMapping("/{id}")
    public void updateLibrary(@PathVariable UUID id, @RequestBody LibraryRequest request) {
        Library library = mapper.map(id, request);
        service.update(id, library);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable UUID id) {
        service.delete(id);
    }
}
