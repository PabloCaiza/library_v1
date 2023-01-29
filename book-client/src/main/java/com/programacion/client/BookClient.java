package com.programacion.client;
import com.programacion.dto.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/book-service/books")
public interface BookClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Book findById(@PathParam("id") Integer id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Book> findAll ();
    @DELETE
    @Path("/{id}")
    Response delete (@PathParam("id") Integer id);
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(Book b);
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response update (Book b, @PathParam("id") Integer id);
    @GET
    @Path("/author/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Book> findAllByAuthor(@PathParam("id") Integer id);

}
