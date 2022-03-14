package com.aditya.librarymanagement.repository.mongo;

import com.aditya.librarymanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book,Integer> {

    public Book findByBookId(Integer bookId);
    public Book findByTitle(String title);
    public void deleteByBookId(Integer bookId);
    public Boolean existsByBookId(Integer bookId);
}
