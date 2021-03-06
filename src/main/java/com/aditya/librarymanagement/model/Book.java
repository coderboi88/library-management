package com.aditya.librarymanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "books")
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    private int bookId;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String category;

    public Book(){}

    public Book(int bookId, String title, String description, String author, String publisher, String category) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
