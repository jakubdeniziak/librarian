package com.jakubdeniziak.librarian.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class LibrariesResponse {

    @Value
    @Builder
    public static class Library {

        UUID id;
        String name;
        String address;

    }

    List<Library> libraries;
}
