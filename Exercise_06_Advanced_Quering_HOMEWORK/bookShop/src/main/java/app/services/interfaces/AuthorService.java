package app.services.interfaces;

import app.entities.Author;

import java.util.List;

public interface AuthorService
{
    Author findById(long id);

    void saveAuthor(Author author);

    List<Author> getAll();

    List<Author> getAllWithNameEndingWith(String endsWith);

    List<Object[]> getTotalBookCopiesByAuthor();
}
