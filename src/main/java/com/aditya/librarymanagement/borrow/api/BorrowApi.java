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

    @GetMapping("/u/getborrowitem/{bookid}")
    public Borrow getBorrowedBook(@PathVariable String bookid){
        return borrowService.getBorrowedBook(Integer.parseInt(bookid));
    }

    @PostMapping("/r/postborrowitem/{bookid}")
    public String createBorrowItem(@PathVariable String bookid){
        return borrowService.createBorrowItem(Integer.parseInt(bookid));
    }

    @GetMapping("/u/getborrowbooks")
    public List<Borrow> getBorrowBooksByUserId(){
        return borrowService.getBorrowBooksByUserId();
    }

    @PutMapping("/r/returnbook/{bookId}")
    public String returnBorrowedBook(@PathVariable String bookId){
        return borrowService.returnBorrowedBook(bookId);
    }

    @DeleteMapping("/r/deleteborrowitem/{borrowId}")
    public String deleteBorrowBook(@PathVariable String borrowId){
        return borrowService.deleteBorrowBook(borrowId);
    }


}
