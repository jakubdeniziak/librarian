package com.jakubdeniziak.librarian.library.dto;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class LibrariesResponse {

    @Value
    public static class Library {

        UUID id;
        String name;
        String address;

    }

    List<Library> libraries;
}
