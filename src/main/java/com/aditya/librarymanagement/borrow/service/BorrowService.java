package com.aditya.librarymanagement.borrow.service;

import com.aditya.librarymanagement.borrow.model.Borrow;
import com.aditya.librarymanagement.borrow.repository.BorrowRepository;
import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.repository.mongo.BookRepository;
import com.aditya.librarymanagement.service.SequenceGeneratorService;
import com.aditya.librarymanagement.service.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BorrowService {

    private BorrowRepository borrowRepository;
    private BookRepository bookRepository;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public Borrow getBorrowedBook(int bookid) {
        if(!bookRepository.existsByBookId(bookid)){
            System.out.println("Book Doesnot exists");
            return new Borrow();
        }
        else if(!borrowRepository.existsByBookId(bookid)){
            System.out.println("This Book hasn't been Borrowed Yet!");
            return new Borrow();
        }
        else {
            return borrowRepository.findByBookId(bookid);
        }
    }

    public String createBorrowItem(int bookid) {
        String userId = getUserId();
        Book book = bookRepository.findByBookId(bookid);

        if(Objects.isNull(book)){
            System.out.println("Book Doesnot Exists");
            return "Book Not exists, Sorry Not able to provide you the Book";
        }
        Borrow borrow = new Borrow(sequenceGeneratorService.generateSequence(Borrow.SEQUENCE_NAME),
                userId,
                book.getBookId(),
                getCurrentDate(),
                book.getTitle(),
                0);
        borrowRepository.save(borrow);
        return "Successfully Borrowed";
    }

    public List<Borrow> getBorrowBooksByUserId() {
        String userId = getUserId();
        return borrowRepository.findAllByUserId(userId);
    }

    public String returnBorrowedBook(String bookId) {
        Borrow borrow = borrowRepository.findByBookId(Integer.parseInt(bookId));
        int diff = getNumberOfDays(borrow.getDateOfIssue(),getCurrentDate());
        if(diff>15){
            borrow.setFine((diff-15)*Borrow.RATE_OF_FINE);
        }
        borrow.setDateOfReturn(getCurrentDate());
        borrowRepository.save(borrow);
        return "Successfully Updated";
    }

    public String deleteBorrowBook(String borrowId) {
        borrowRepository.deleteById(Integer.parseInt(borrowId));
        return "SuccessFully Deleted";
    }

    public List<Borrow> getBorrowBookList() {
        return borrowRepository.findAll();
    }

    //Get Userid
    private String getUserId(){
        UserDetailImpl userDetails = (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getId();
    }

    //Get Current Date
    private String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(calendar.getTime());
    }

    //Calculate Total no. of Days between two dates
    private int getNumberOfDays(String issueDate,String returnDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        int diff=0;
        try {
            Date date1 = simpleDateFormat.parse(issueDate);
            Date date2 = simpleDateFormat.parse(returnDate);
            diff = (int) ((date2.getTime() - date1.getTime())/(1000*60*60*24));
            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }
}
