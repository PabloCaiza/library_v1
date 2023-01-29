package com.programacion;


import com.programacion.config.WebConfig;
import com.programacion.controller.AuthorController;
import com.programacion.controller.BookController;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Application {

    public static void main(String[] args) {
        SeContainer seContainer = SeContainerInitializer
                .newInstance()
                .initialize();

        new WebConfig(
                seContainer.select(AuthorController.class).get(),
                seContainer.select(BookController.class).get());
    }
}
