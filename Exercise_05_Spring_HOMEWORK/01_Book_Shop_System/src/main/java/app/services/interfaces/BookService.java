package app.services.interfaces;

import app.entities.Book;

import java.text.ParseException;
import java.util.List;

public interface BookService
{
    void save(Book book);

    List<Book> findByDate() throws ParseException;

    List<Book> findBookByGeorgePowell();
}
