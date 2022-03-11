package com.aditya.librarymanagement.model.request;

public class BookRequest {

    private String title;
    private String description;
    private String author;
    private String publisher;
    private String category;

    public BookRequest(){}

    public BookRequest(String title, String description, String author, String publisher, String category) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
