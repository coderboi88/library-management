package com.aditya.librarymanagement.borrow.api;

import com.aditya.librarymanagement.borrow.model.Borrow;
import com.aditya.librarymanagement.borrow.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowApi {

    private BorrowService borrowService;

    @Autowired
    public BorrowApi(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    //User Can check availability of a book
    @GetMapping("/u/getborrowbook/{bookid}")
    public Borrow getBorrowedBook(@PathVariable String bookid){
        return borrowService.getBorrowedBook(Integer.parseInt(bookid));
    }

    //User can check list of book borrowed by him
    @GetMapping("/u/getborrowbooks")
    public List<Borrow> getBorrowBooksByUserId(){
        return borrowService.getBorrowBooksByUserId();
    }

    //Librarian will issue the book
    @PostMapping("/r/issuebook/{bookid}")
    public String createBorrowItem(@PathVariable String bookid){
        return borrowService.createBorrowItem(Integer.parseInt(bookid));
    }

    //Librarian can check All the borrowed books
    @GetMapping("/r/getborrowbooklist")
    public List<Borrow> getBorrowedBooksList(){
        return borrowService.getBorrowBookList();
    }

    //Librarian will update while user return the book
    @PutMapping("/r/returnbook/{bookId}")
    public String returnBorrowedBook(@PathVariable String bookId){
        return borrowService.returnBorrowedBook(bookId);
    }

    //Librarian Can delete the borrow data
    @DeleteMapping("/r/deleteborrowbook/{borrowId}")
    public String deleteBorrowBook(@PathVariable String borrowId){
        return borrowService.deleteBorrowBook(borrowId);
    }

}
