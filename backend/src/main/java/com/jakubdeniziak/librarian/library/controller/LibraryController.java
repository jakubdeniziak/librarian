package com.jakubdeniziak.librarian.library.controller;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;

import java.util.UUID;

public interface LibraryController {

    void create(UUID id, LibraryRequest request);
    LibraryResponse read(UUID id);
    LibrariesResponse readAll();
    void update(UUID id, LibraryRequest request);
    void delete(UUID id);

}
