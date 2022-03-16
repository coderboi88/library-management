package com.aditya.librarymanagement.service;

import com.aditya.librarymanagement.model.Book;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchService {

    //It is a service class for All ElasticsearchOperation manually.

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ElasticSearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public String createProductIndex(Book book) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(String.valueOf(book.getBookId()))
                .withObject(book).build();

        String documentId = elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of("books"));

        return documentId;
    }

    public Book findBookByTitle(final String title) {

        QueryBuilder queryBuilder =
                 QueryBuilders.matchQuery("title", title);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<Book> bookHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Book.class,
                                IndexCoordinates.of("books"));
        return bookHits.getSearchHit(0).getContent();
    }

    public Book findByCategory(final String category) {
        Query searchQuery = new StringQuery(
                "{\"match\":{\"category\":{\"query\":\""+ category + "\"}}}\"");

        SearchHits<Book> books = elasticsearchOperations.search(
                searchQuery,
                Book.class,
                IndexCoordinates.of("books"));
        return books.getSearchHit(0).getContent();
    }

    public Book findByBookId(final int bookid) {
        Criteria criteria = new Criteria("bookId").is(bookid);
        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<Book> books = elasticsearchOperations
                .search(searchQuery,
                        Book.class,
                        IndexCoordinates.of("books"));
        return books.getSearchHit(0).getContent();
    }

}
