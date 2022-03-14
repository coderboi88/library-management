package com.aditya.librarymanagement.borrow.repository;

import com.aditya.librarymanagement.borrow.model.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends MongoRepository<Borrow,Integer> {

    public Borrow findByBookTitle(String title);
    public Borrow findByBookId(int bookId);
    public Boolean existsByBookId(int bookId);
    public List<Borrow> findAllByUserId(String userId);
}
