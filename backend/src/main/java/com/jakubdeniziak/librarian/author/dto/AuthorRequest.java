package com.jakubdeniziak.librarian.author.dto;

import lombok.Getter;


@Getter
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private String description;
}
