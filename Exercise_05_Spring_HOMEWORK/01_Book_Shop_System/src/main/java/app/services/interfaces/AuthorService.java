package app.services.interfaces;

import app.entities.Author;

import java.util.List;

public interface AuthorService
{
    Author findById(long id);

    void saveAuthor(Author author);

    public List<Author> getAll();

    List<Author> getAllWithBookBefore1990();

    List<Author> getAllAuthorsOrderByNumberOfBooks();
}
