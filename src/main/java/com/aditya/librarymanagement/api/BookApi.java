package com.aditya.librarymanagement.api;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.model.request.LoginRequest;
import com.aditya.librarymanagement.model.request.SignupRequest;
import com.aditya.librarymanagement.service.AuthenticationService;
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
    private AuthenticationService authenticationService;

    @Autowired
    public BookApi(BookService bookService, AuthenticationService authenticationService) {
        this.bookService = bookService;
        this.authenticationService = authenticationService;
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
        return authenticationService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationService.authenticateUser(loginRequest);
    }

    @GetMapping("/getfastbook/{bookId}")
    public Book getFastBook(@PathVariable String bookId){
        return bookService.getFastBook(bookId);
    }
}
