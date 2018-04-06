package app;

import app.services.interfaces.AuthorService;
import app.services.interfaces.BookService;
import app.services.interfaces.CategorieService;
import app.unitlities.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner
{
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategorieService categorieService;
    private final DataParser dataParser;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategorieService categorieService, DataParser dataParser)
    {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categorieService = categorieService;
        this.dataParser = dataParser;
    }

    @Override
    public void run(String... args) throws Exception
    {
        dataParser.parseAuthors();
        dataParser.parseCategories();
        dataParser.parseBooks();
        //Write Queries
        getAllBooksAfter2000();
        getAuthorsWithBooksBfore1990();
        getAllAuthorsByOrder();
        getBooksByGeorgePowell();

    }

    private void getAllBooksAfter2000() throws ParseException
    {
        this.bookService.findByDate().forEach(e -> System.out.println(e.getTitle()));
    }

    private void getAuthorsWithBooksBfore1990()
    {
        this.authorService.getAllWithBookBefore1990().forEach(e -> System.out.printf("%s %s\n", e.getFirstName(), e.getLastName()));
    }

    private void getAllAuthorsByOrder()
    {
        this.authorService.getAllAuthorsOrderByNumberOfBooks().forEach(e ->
        {
            System.out.printf("%s %s %d\n", e.getFirstName(), e.getLastName(), e.getBooks().size());
        });
    }

    private void getBooksByGeorgePowell()
    {
        this.bookService.findBookByGeorgePowell().forEach(e ->
                System.out.printf("%s %s %s\n", e.getAuthor().getFirstName(), e.getAuthor().getLastName(), e.getTitle()));
    }
}
