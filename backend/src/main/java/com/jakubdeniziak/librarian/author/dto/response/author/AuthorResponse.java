package com.jakubdeniziak.librarian.author.dto.response.author;

import java.util.UUID;

public interface AuthorResponse {

    UUID getId();
    String getFirstName();
    String getLastName();
    String getDescription();

}
