package com.aditya.librarymanagement.borrow.model;

public class BorrowRequest {

    private String title;

    public BorrowRequest(){}

    public BorrowRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
