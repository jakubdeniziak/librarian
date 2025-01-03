package com.jakubdeniziak.librarian.data.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class DataResponse {

    List<DataFormat.Author> authors;
    List<DataFormat.Publisher> publishers;
    List<DataFormat.Library> libraries;
    List<DataFormat.Book> books;
    List<DataFormat.LibraryBook> libraryBooks;

}
