package com.jakubdeniziak.librarian.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LibraryRequest {

    String name;
    String address;
    String description;

}
