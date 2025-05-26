package com.jakubdeniziak.librarian.library.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class LibraryResponse {

    UUID id;
    String name;
    String address;
    String description;

}
