package com.aditya.librarymanagement.api.restricted;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/r")
public class RestrictedBookApi {

    private BookService bookService;

    @Autowired
    public RestrictedBookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addbook")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PostMapping("/addbooks")
    public List<Book> addBooks(@RequestBody List<Book> books){
        return bookService.addBooks(books);
    }

    @PutMapping("/updatebook")
    public void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }

    @DeleteMapping("/deletebook/{bookId}")
    public void deleteBook(@PathVariable String bookId){
        bookService.deleteBook(bookId);
    }
}
