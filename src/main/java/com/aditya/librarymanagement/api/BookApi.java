package com.aditya.librarymanagement.api;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.model.request.LoginRequest;
import com.aditya.librarymanagement.model.request.SignupRequest;
import com.aditya.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/u")
public class BookApi {

    private BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getbooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("getbook/{bookId}")
    public Book getBook(@PathVariable String bookId){
        return bookService.getBook(bookId);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return bookService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return bookService.authenticateUser(loginRequest);
    }
}
