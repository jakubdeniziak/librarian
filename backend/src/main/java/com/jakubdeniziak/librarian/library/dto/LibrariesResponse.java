package com.jakubdeniziak.librarian.library.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class LibrariesResponse {
    List<Library> libraries;

    @Builder
    @Getter
    public static class Library {
        private UUID id;
        private String name;
        private String address;
    }
}
