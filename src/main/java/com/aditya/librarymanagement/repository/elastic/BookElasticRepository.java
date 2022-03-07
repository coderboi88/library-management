package com.aditya.librarymanagement.repository.elastic;

import com.aditya.librarymanagement.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookElasticRepository extends ElasticsearchRepository<Book,Integer> {

    public void deleteByBookId(Integer bookId);

    public Book findByBookId(Integer bookId);
}
