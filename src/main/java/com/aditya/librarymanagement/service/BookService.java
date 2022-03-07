package com.aditya.librarymanagement.service;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.model.ERole;
import com.aditya.librarymanagement.model.Role;
import com.aditya.librarymanagement.model.User;
import com.aditya.librarymanagement.model.request.LoginRequest;
import com.aditya.librarymanagement.model.request.SignupRequest;
import com.aditya.librarymanagement.model.response.JwtResponse;
import com.aditya.librarymanagement.model.response.MessageResponse;
import com.aditya.librarymanagement.repository.elastic.BookElasticRepository;
import com.aditya.librarymanagement.repository.mongo.BookRepository;
import com.aditya.librarymanagement.repository.mongo.RoleRepository;
import com.aditya.librarymanagement.repository.mongo.UserRepository;
import com.aditya.librarymanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookElasticRepository bookElasticRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookElasticRepository bookElasticRepository) {
        this.bookRepository = bookRepository;
        this.bookElasticRepository = bookElasticRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        bookElasticRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addBooks(List<Book> books) {
        bookRepository.saveAll(books);
        bookElasticRepository.saveAll(books);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
        bookElasticRepository.save(book);
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteByBookId(Integer.parseInt(bookId));
        bookElasticRepository.deleteByBookId(Integer.parseInt(bookId));
    }

    public Book getBook(String bookId) {
        return bookRepository.findByBookId(Integer.parseInt(bookId));
    }


    public Book getFastBook(String bookId) {
        return bookElasticRepository.findByBookId(Integer.parseInt(bookId));
    }
}
