package com.jakubdeniziak.librarian.library.dto;

import lombok.Value;

@Value
public class LibraryRequest {

    String name;
    String address;
    String description;

}
