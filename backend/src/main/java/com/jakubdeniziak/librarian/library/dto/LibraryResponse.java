package com.jakubdeniziak.librarian.library.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class LibraryResponse {

    UUID id;
    String name;
    String address;
    String description;

}
