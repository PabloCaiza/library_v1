package com.distribuida.service;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();
    Author getAuthorById(Integer id);
    void createAuthor(Author author);
    void updateAuthor(Integer id,Author author);
    void delete(Integer id);
}
