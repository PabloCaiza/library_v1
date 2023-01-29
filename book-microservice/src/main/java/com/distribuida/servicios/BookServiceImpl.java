package com.distribuida.servicios;

import com.distribuida.db.Book;
import com.distribuida.mapper.BookMapper;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookServiceImpl implements BookService {
    @Inject
    private DbClient dbClient;

    @Override
    public List<Book> getBooks() throws ExecutionException, InterruptedException {
        return dbClient.execute(dbExecute -> dbExecute.createNamedQuery("select-all-books").execute())
                .collectList()
                .get()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toList());


    }

    @Override
    public Book getBookById(Integer id) throws ExecutionException, InterruptedException {

        return dbClient.execute(dbExecute -> dbExecute.createNamedQuery("select-book").addParam("id", id).execute())
                .first()
                .map(BookMapper::map)
                .get();

    }


    @Override
    public void creteBook(Book book) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.createNamedInsert("create-book")
                .addParam("isbn", book.getIsbn())
                .addParam("title", book.getTitle())
                .addParam("author", book.getAuthor())
                .addParam("price", book.getPrice())
                .execute()).get();
    }

    @Override
    public void updateBook(Integer id, Book book) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.createNamedUpdate("update-book")
                        .addParam("isbn", book.getIsbn())
                        .addParam("title", book.getTitle())
                        .addParam("author", book.getAuthor())
                        .addParam("price", book.getPrice())
                        .addParam("id", id)
                        .execute())
                .get();


    }

    @Override
    public void delete(Integer id) throws ExecutionException, InterruptedException {
        dbClient.execute(dbExecute -> dbExecute.createNamedDelete("delete-book")
                        .addParam("id", id)
                        .execute())
                .get();

    }

    @Override
    public List<Book> getBookByAuthor(Integer authorId) throws ExecutionException, InterruptedException {
        return dbClient.execute(dbExecute -> dbExecute.createNamedQuery("select-books-author")
                        .addParam("authorId",authorId)
                        .execute())
                .collectList()
                .get()
                .stream()
                .map(BookMapper::map)
                .collect(Collectors.toList());

    }
}
