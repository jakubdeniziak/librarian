package com.jakubdeniziak.librarian.library.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class LibraryResponse {
    private UUID id;
    private String name;
    private String address;
}
