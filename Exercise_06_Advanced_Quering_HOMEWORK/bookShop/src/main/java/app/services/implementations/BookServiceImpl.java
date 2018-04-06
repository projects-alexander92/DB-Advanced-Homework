package app.services.implementations;

import app.entities.Book;
import app.entities.enums.AgeRestriction;
import app.entities.enums.EditionType;
import app.repositories.BookRepository;
import app.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PreRemove;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book)
    {
        this.bookRepository.save(book);
    }

    @Override
    public List<Book> getTitleByAgeRestriction(String value)
    {
        int enumParam = 0;
        AgeRestriction[] values = AgeRestriction.values();
        for (AgeRestriction ageRestriction : values)
        {
            if (ageRestriction.toString().toLowerCase().equals(value.toLowerCase()))
            {
                enumParam = ageRestriction.getValue();
                break;
            }
        }
        return this.bookRepository.getAllByAgeRestriction(enumParam);
    }

    @Override
    public List<Book> getAllGoldenBooks()
    {
        return this.bookRepository.getAllGoldenBooksWithLessThen500Copies();
    }

    @Override
    public List<Book> getByPriceInBoundaries()
    {
        return this.bookRepository.getByPriceInBoundaries();
    }

    @Override
    public List<Book> getBooksNotReleasedIn(String value) throws ParseException
    {
        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(value + "/00/00");
        return this.bookRepository.notRealeasedBooks(date);
    }

    @Override
    public List<Book> booksReleasedBeforeDate(String value) throws ParseException
    {
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(value);
        return this.bookRepository.realeasedBeforeDate(date);
    }

    @Override
    public List<Book> getAllBooksContainingValueInTitle(String value)
    {
        return this.bookRepository.getAllByTitleContaining(value);
    }

    @Override
    public List<Book> getByAuthorLastName(String value)
    {
        value = value + "%";
        return this.bookRepository.bookTitlesSearch(value);
    }

    @Override
    public int getByTitleLength(int value)
    {
        return this.bookRepository.getBookTitleLenght(value);
    }

    @Override
    public Object[] getBookInfoByTitle(String value)
    {
        Object[] bookObject = this.bookRepository.selectBookInfo(value).get(0);
        Object[] bookInfoValues = new Object[bookObject.length];
        AgeRestriction[] ageRestrictions = AgeRestriction.values();
        EditionType[] editionTypes = EditionType.values();
        for (AgeRestriction ageRestriction : ageRestrictions)
        {
            if (ageRestriction.getValue() == (int) bookObject[2])
            {
                bookInfoValues[2] = ageRestriction.toString();
                break;
            }
        }
        for (EditionType editionType : editionTypes)
        {
            if (editionType.getValue() == (int) bookObject[1])
            {
                bookInfoValues[1] = editionType.toString();
                break;
            }
        }
        bookInfoValues[0] = bookObject[0];
        bookInfoValues[3] = bookObject[3];
        return bookInfoValues;
    }

    @Override
    public int increaseBookCopies(String dateString, int copiesCount) throws ParseException
    {
        Date date = new SimpleDateFormat("dd MMM yyyy").parse(dateString);
        this.bookRepository.increaseBookCopies(copiesCount, date);
        return this.bookRepository.increaseBookCopies(copiesCount, date);
    }

    @Override
    @Modifying
    @Transactional
    public int deleteBooksByCopies(int copiesCount)
    {
        return this.bookRepository.deleteByCopiesLessThan(copiesCount);
    }
}
