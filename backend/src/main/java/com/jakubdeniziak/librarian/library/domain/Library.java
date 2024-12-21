package com.jakubdeniziak.librarian.library.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Library {

    private UUID id;
    private String name;
    private String address;
    private String description;

}
