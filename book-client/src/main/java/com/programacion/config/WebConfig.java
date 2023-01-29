package com.programacion.config;


import com.programacion.controller.AuthorController;
import com.programacion.controller.BookController;

import static spark.Spark.*;

//hold the web  routes configuration
public class WebConfig {


    private AuthorController authorController;
    private BookController bookController;

    public WebConfig( AuthorController authorController,
                     BookController bookController){

        this.authorController=authorController;
        this.bookController=bookController;
        port(8084);
        staticFileLocation("/public");
        setupRoutes();
        setupFilters();
    }
    public void setupRoutes(){
        get("/",authorController::getAuthors);
        get("/addAuthor",authorController::showAddAuthor);
        post("/addAuthor",authorController::addAuthor);
        get("/updateAuthor/:id",authorController::showUpdateAuthor);
        get("/deleteAuthor/:id",authorController::deleteAuthor);
        get("/books/author/:authorId",bookController::getBooks);
        get("/addBook",bookController::showAddBook);
        post("/addBook",bookController::addBook);
        get("/updateBook/:id",bookController::showUpdateBook);
        get("/:authorId/deleteBook/:id",bookController::deleteBook);


    }
    public void setupFilters(){
        after((request, response) -> {
            response.type("text/html");
        });
    }


}
