package com.programacion.client;


import com.programacion.dto.Author;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/author-service/authors")
public interface AuthorClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Author findById(@PathParam("id") Integer id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Author> findAll();

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Integer id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(Author a);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(Author a, @PathParam("id") Integer id);
}