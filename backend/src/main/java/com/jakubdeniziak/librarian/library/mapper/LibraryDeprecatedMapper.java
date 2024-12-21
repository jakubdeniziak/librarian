package com.jakubdeniziak.librarian.library.mapper;

import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;
import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Deprecated
public class LibraryDeprecatedMapper {

    public LibraryEntity mapToEntity(Library library) {
        return LibraryEntity.builder().build();
    }

    public List<Library> mapToDomain(List<LibraryEntity> entities) {
        return List.of();
    }

    public Optional<Library> map(Optional<LibraryEntity> entity) {
        return Optional.empty();
    }

    public LibraryResponse map(Library library) {
        return new LibraryResponse(null, null, null, null);
    }

    public LibrariesResponse map(List<Library> libraries) {
        return new LibrariesResponse(null);
    }

    public Library map(UUID id, LibraryRequest request) {
        return new Library();
    }
}
