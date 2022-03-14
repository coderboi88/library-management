package com.aditya.librarymanagement.borrow.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "borrows")
public class Borrow {

    @Transient
    public static final String SEQUENCE_NAME = "borrows_sequence";

    @Id
    private int borrowId;
    private String userId;
    private int bookId;
    private String dateOfIssue;
    private String dateOfReturn;
    private String bookTitle;
    private int fine;

    public static final int RATE_OF_FINE = 1;

    public Borrow(){}

    public Borrow(int borrowId, String userId, int bookId, String dateOfIssue, String bookTitle, int fine) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.dateOfIssue = dateOfIssue;
        this.bookTitle = bookTitle;
        this.fine = fine;
    }

    public Borrow(int borrowId, String userId, int bookId, String dateOfIssue, String dateOfReturn, String bookTitle, int fine) {
        this.borrowId = borrowId;
        this.userId = userId;
        this.bookId = bookId;
        this.dateOfIssue = dateOfIssue;
        this.dateOfReturn = dateOfReturn;
        this.bookTitle = bookTitle;
        this.fine = fine;
    }

    public Borrow(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
