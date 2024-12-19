package com.jakubdeniziak.librarian.author.dto;

import lombok.Value;

@Value
public class AuthorRequest {

    String firstName;
    String lastName;
    String description;

}
