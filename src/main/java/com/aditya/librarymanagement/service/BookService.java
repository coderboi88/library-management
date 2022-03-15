package com.aditya.librarymanagement.service;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.model.request.BookRequest;
import com.aditya.librarymanagement.repository.elastic.BookElasticRepository;
import com.aditya.librarymanagement.repository.mongo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookElasticRepository bookElasticRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public BookService(BookRepository bookRepository, BookElasticRepository bookElasticRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.bookRepository = bookRepository;
        this.bookElasticRepository = bookElasticRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public Book addBook(BookRequest bookRequest) {
        Book book =  new Book(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME),
                bookRequest.getTitle(),
                bookRequest.getDescription(),
                bookRequest.getAuthor(),
                bookRequest.getPublisher(),
                bookRequest.getCategory());
        bookRepository.save(book);
        return bookElasticRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> addBooks(List<BookRequest> booksRequests) {
        List<Book> books = new ArrayList<>();
        for(BookRequest bookRequest : booksRequests){
            Book book =  new Book(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME),
                    bookRequest.getTitle(),
                    bookRequest.getDescription(),
                    bookRequest.getAuthor(),
                    bookRequest.getPublisher(),
                    bookRequest.getCategory());
            books.add(book);
        }

        bookRepository.saveAll(books);

        List<Book> bookList = new ArrayList<>();
        bookElasticRepository.saveAll(books).forEach(bookList::add);
        return bookList;
    }

    public void updateBook(BookRequest bookRequest) {
        Book book = bookRepository.findByTitle(bookRequest.getTitle());
        if(Objects.isNull(book)) {
            book.setBookId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
        }
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setCategory(bookRequest.getCategory());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
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

    public List<Book> getBookByCategory(String category) {
        return bookRepository.getBookFilteredByCategory(category);
    }
}
