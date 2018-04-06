package app.services.interfaces;

import app.entities.Book;
import org.springframework.data.jpa.repository.Query;

import java.text.ParseException;
import java.util.List;

public interface BookService
{
    void save(Book book);

    List<Book> getTitleByAgeRestriction(String value);

    List<Book> getAllGoldenBooks();

    List<Book> getByPriceInBoundaries();

    List<Book> getBooksNotReleasedIn(String value) throws ParseException;

    List<Book> booksReleasedBeforeDate(String value) throws ParseException;

    List<Book> getAllBooksContainingValueInTitle(String value);

    List<Book> getByAuthorLastName(String value);

    int getByTitleLength(int value);

    Object[] getBookInfoByTitle(String value);

    int increaseBookCopies(String dateString, int copiesCount) throws ParseException;

    int deleteBooksByCopies(int copiesCount);
}
