package com.jakubdeniziak.librarian.library.mapper;

import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class LibraryDefaultMapper implements LibraryMapper {

    @Override
    public Library map(UUID id, LibraryRequest request) {
        return Library.builder()
                .id(id)
                .name(request.getName())
                .address(request.getAddress())
                .description(request.getDescription())
                .build();
    }

    @Override
    public LibraryEntity map(Library library) {
        return LibraryEntity.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .description(library.getDescription())
                .build();
    }

    @Override
    public Library map(LibraryEntity library) {
        return Library.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .description(library.getDescription())
                .build();
    }

    @Override
    public List<Library> map(List<LibraryEntity> libraries) {
        return libraries.stream()
                .map(this::map)
                .toList();
    }

    @Override
    public LibraryResponse mapToResponse(Library library) {
        return LibraryResponse.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .description(library.getDescription())
                .build();
    }

    @Override
    public LibrariesResponse mapToResponse(List<Library> libraries) {
        List<LibrariesResponse.Library> responseLibraries = libraries.stream()
                .map(library -> LibrariesResponse.Library.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .build())
                .toList();
        return LibrariesResponse.builder()
                .libraries(responseLibraries)
                .build();
    }

}
