package com.jakubdeniziak.librarian.library;

import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LibraryMapper {
    public LibraryResponse map(Library library) {
        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .build();
    }

    public LibrariesResponse map(List<Library> libraries) {
        return LibrariesResponse.builder()
                .libraries(libraries.stream()
                        .map(library -> LibrariesResponse.Library.builder()
                                .id(library.getId())
                                .name(library.getName())
                                .address(library.getAddress())
                                .build())
                        .toList())
                .build();
    }

    public Library map(UUID id, LibraryRequest request) {
        return Library.builder()
                .id(id)
                .name(request.getName())
                .address(request.getAddress())
                .build();
    }
}
