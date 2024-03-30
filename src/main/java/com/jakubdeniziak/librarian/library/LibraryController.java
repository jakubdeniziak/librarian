package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;

import java.util.UUID;

public interface LibraryController {
    LibraryResponse readLibrary(UUID id);

    LibrariesResponse readLibraries();

    void createLibrary(UUID id, LibraryRequest request);

    void updateLibrary(UUID id, LibraryRequest request);

    void deleteLibrary(UUID id);
}
