package com.jakubdeniziak.librarian.publisher.dto;

import com.jakubdeniziak.librarian.validation.url.ValidUrl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class PublisherRequest {

    @NotBlank(message = "Publisher name must not be blank.")
    String name;
    @ValidUrl
    String websiteUrl;
    @Size(max = 1000, message = "Description must not exceed 1000 characters.")
    String description;

}
