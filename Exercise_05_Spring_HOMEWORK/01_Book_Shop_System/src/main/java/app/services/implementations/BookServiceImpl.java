package app.services.implementations;

import app.entities.Book;
import app.repositories.BookRepository;
import app.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
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
    public List<Book> findByDate() throws ParseException
    {
        Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2000/00/00");
        return this.bookRepository.findByReleaseDateAfter(date);
    }

    @Override
    public List<Book> findBookByGeorgePowell()
    {
        return this.bookRepository.findBookByGeorgePowell();
    }
}
