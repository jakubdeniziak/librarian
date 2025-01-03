package com.jakubdeniziak.librarian.data.dto;

import lombok.Value;

import java.util.List;

@Value
public class DataRequest {

    List<DataFormat.Author> authors;
    List<DataFormat.Publisher> publishers;
    List<DataFormat.Library> libraries;
    List<DataFormat.User> users;
    List<DataFormat.Book> books;
    List<DataFormat.LibraryBook> libraryBooks;
    List<DataFormat.UserBook> userBooks;

}
