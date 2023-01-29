package com.distribuida.service;

import com.distribuida.db.Author;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;


@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {

    @Override
    public List<Author> getAuthors() {
        return Author.listAll();
    }

    @Override
    public Author getAuthorById(Integer id) {
        return Author.findById(id);
    }


    @Transactional
    @Override
    public void createAuthor(Author author) {
        author.persist();

    }
    @Transactional
    @Override
    public void updateAuthor(Integer id, Author author) {
        Author savedAuthor=Author.findById(id);
        savedAuthor.setFirst_name(author.getFirst_name());
        savedAuthor.setLast_name(author.getLast_name());
        savedAuthor.persist();




    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Author.deleteById(id);

    }
}
