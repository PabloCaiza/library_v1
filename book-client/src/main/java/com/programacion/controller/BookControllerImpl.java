package com.programacion.controller;

import com.programacion.client.AuthorClient;
import com.programacion.client.BookClient;
import com.programacion.dto.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class BookControllerImpl implements BookController {

    @Inject
    private BookClient bookClient;
    @Inject
    private AuthorClient authorClient;

    @Override
    public String getBooks(Request request, Response response) {
        Integer authorId = Integer.parseInt(request.params(":authorId"));
        Map<String, Object> model = new HashMap<>();
        model.put("author", authorClient.findById(authorId));
        model.put("books", bookClient.findAllByAuthor(authorId));
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "books"));
    }

    @Override
    public String showAddBook(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("book", new Book());
        model.put("authors", authorClient.findAll());
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "addBook"));
    }

    @Override
    public String addBook(Request request, Response response) throws ParseException {
        MultiMap<String> params = new MultiMap<String>();
        UrlEncoded.decodeTo(request.body(), params, "UTF-8");
        var book=Book.builder()
                .isbn(params.getString("isbn"))
                .author(Integer.parseInt(params.getString("author")))
                .title(params.getString("title"))
                .price(new BigDecimal(params.getString("price")))
                .build();
        String id = params.getString("id");
        if(!id.isEmpty()){
            book.setId(Integer.parseInt(id));
            bookClient.update(book,Integer.parseInt(id));
        }else{
            bookClient.create(book);
        }
        response.redirect(String.format("/books/author/%d",book.getAuthor()));
        return "";
    }

    @Override
    public String showUpdateBook(Request request, Response response) {
        Integer id = Integer.parseInt(request.params(":id"));
        Map<String, Object> model = new HashMap<>();
        model.put("book", bookClient.findById(id));
        model.put("authors", authorClient.findAll());
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "addBook"));
    }

    @Override
    public String deleteBook(Request request, Response response) {
        Integer authorId=Integer.parseInt(request.params(":authorId"));
        Integer id = Integer.parseInt(request.params(":id"));
        bookClient.delete(id);
        response.redirect(String.format("/books/author/%d",authorId));
        return "";
    }
}
