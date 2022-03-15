package com.aditya.librarymanagement.util;

import com.aditya.librarymanagement.model.Book;
import com.aditya.librarymanagement.repository.mongo.CustomBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomBookImpl implements CustomBookRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Book> getBookFilteredByCategory(String category) {
        Query query= new Query();
        query.addCriteria(Criteria.where("category").is(category));

        return mongoTemplate.find(query,Book.class);
    }
}
