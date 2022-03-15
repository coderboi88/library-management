package com.aditya.librarymanagement.repository.mongo;

import com.aditya.librarymanagement.model.Book;

import java.util.List;

public interface CustomBookRepository {

    List<Book> getBookFilteredByCategory(String category);
}
