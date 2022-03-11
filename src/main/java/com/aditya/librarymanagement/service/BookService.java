package com.aditya.librarymanagement.service;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.repository.elastic.BookElasticRepository;
import com.aditya.librarymanagement.repository.mongo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookElasticRepository bookElasticRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookElasticRepository bookElasticRepository) {
        this.bookRepository = bookRepository;
        this.bookElasticRepository = bookElasticRepository;
    }

    public Book addBook(Book book) {
        bookRepository.save(book);
        return bookElasticRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> addBooks(List<Book> books) {
        bookRepository.saveAll(books);

        List<Book> bookList = new ArrayList<>();
        bookElasticRepository.saveAll(books).forEach(bookList::add);
        return bookList;
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
