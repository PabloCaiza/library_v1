package com.programacion.config;

import com.programacion.client.AuthorClient;
import com.programacion.client.BookClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;

@ApplicationScoped
public class Config {

    private final String BASE_URL="http://traefik";


    @ApplicationScoped
    @Produces
    public BookClient bookClient(){
        ResteasyClient client=(ResteasyClient)ClientBuilder.newClient();
        return client.target(BASE_URL).proxy(BookClient.class);

    }
    @ApplicationScoped
    @Produces
    public AuthorClient authorClient(){
        ResteasyClient client=(ResteasyClient)ClientBuilder.newClient();
        return client.target(BASE_URL).proxy(AuthorClient.class);
    }

}
