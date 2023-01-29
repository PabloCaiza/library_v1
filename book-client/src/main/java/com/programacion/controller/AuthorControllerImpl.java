package com.programacion.controller;

import com.programacion.client.AuthorClient;
import com.programacion.dto.Author;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class AuthorControllerImpl implements AuthorController {

    @Inject
    private AuthorClient authorClient;
    @Override
    public String getAuthors(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("authors", authorClient.findAll());
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "authors"));
    }

    @Override
    public String showAddAuthor(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("author", new Author());
        return new ThymeleafTemplateEngine()
                .render(new ModelAndView(model, "addAuthor"));
    }

    @Override
    public String addAuthor(Request request, Response response) throws ParseException {
        MultiMap params = new MultiMap<String>();
        UrlEncoded.decodeTo(request.body(), params, "UTF-8");
        var author=Author.builder()
                .first_name(params.getString("firstName"))
                .last_name(params.getString("lastName"))
                .build();
        var id = params.getString("id");
        if(!id.isEmpty()){
            author.setId(Integer.parseInt(id));
            authorClient.update(author,Integer.parseInt(id));
        }else{
            authorClient.create(author);
        }
        response.redirect("/");
        return "";
    }

    @Override
    public String showUpdateAuthor(Request request, Response response) throws ParseException {
        Integer id = Integer.parseInt(request.params(":id"));
        Map<String, Object> model = new HashMap<>();
        var authorById = authorClient.findById(id);
        model.put("author", authorById);
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, "addAuthor"));
    }

    @Override
    public String deleteAuthor(Request request, Response response) {
        Integer id = Integer.parseInt(request.params(":id"));
        authorClient.delete(id);
        response.redirect("/");
        return "";
    }
}
