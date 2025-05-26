package com.jakubdeniziak.librarian.library.mapper;

import com.jakubdeniziak.librarian.library.domain.Library;
import com.jakubdeniziak.librarian.library.dto.LibrariesResponse;
import com.jakubdeniziak.librarian.library.dto.LibraryRequest;
import com.jakubdeniziak.librarian.library.dto.LibraryResponse;
import com.jakubdeniziak.librarian.library.entity.LibraryEntity;

import java.util.List;
import java.util.UUID;

public interface LibraryMapper {

    Library map(UUID id, LibraryRequest request);
    LibraryEntity map(Library library);
    List<LibraryEntity> map(List<Library> libraries);
    Library mapToDomain(LibraryEntity library);
    List<Library> mapToDomain(List<LibraryEntity> libraries);
    LibraryResponse mapToResponse(Library library);
    LibrariesResponse mapToResponse(List<Library> libraries);

}
