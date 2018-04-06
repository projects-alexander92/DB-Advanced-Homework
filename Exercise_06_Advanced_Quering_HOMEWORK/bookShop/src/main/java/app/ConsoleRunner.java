package app;

import app.services.interfaces.AuthorService;
import app.services.interfaces.BookService;
import app.services.interfaces.CategorieService;
import app.utilities.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    /*!!!!!!!!!*/
    //Различните задачки от това домашно съм ги отделил в различни методи в този клас
    /*!!!!!!!!!*/
    @Override
    public void run(String... args) throws Exception
    {
        dataParser.parseAuthors();
        dataParser.parseCategories();
        dataParser.parseBooks();
        //Problem 01
//        booksTitlesByAgeRestriction();
        //Problem 02
//        goldenBooks();
        //Problem 03
//        getByPriceInBoundaries();
        //Problem 04
//        notReleasedBooks();
        //Problem 05
//        booksReleasedBeforeDate();
        //Problem 06
//        authorsSearch();
        //Problem 07
//        bookSearch();
        //Problem 08
//        bookTitleSSearch();
        //Problem 09
//        countBooks();
        //Problem 10
//        totalBookCopies();
        //Problem 11
//        reducedBook();
        //Problem 12
//        increaseBookCopies();
        //Problem13
//        removeBooks();

    }

    private void removeBooks() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int copiesCount = Integer.parseInt(br.readLine().trim());
        int i = this.bookService.deleteBooksByCopies(copiesCount);
        System.out.printf("%d books were deleted\n", i);
    }

    private void increaseBookCopies() throws IOException, ParseException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String date = br.readLine().trim();
        int copiesCount = Integer.parseInt(br.readLine().trim());
        int effectedRows = this.bookService.increaseBookCopies(date, copiesCount);
        System.out.printf("%d books are released after %s so total of %d book copies were added", effectedRows, date, (effectedRows * copiesCount));
    }

    private void reducedBook() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        Object[] bookInfoByTitle = this.bookService.getBookInfoByTitle(value);
        System.out.printf("%s %s %s %.2f\n", bookInfoByTitle[0], bookInfoByTitle[1], bookInfoByTitle[2], bookInfoByTitle[3]);
    }

    private void totalBookCopies()
    {
        this.authorService.getTotalBookCopiesByAuthor().forEach(e ->
                System.out.printf("%s %s - %d\n", e[0], e[1], e[2]));
    }

    private void countBooks() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int value = Integer.parseInt(br.readLine().trim());
        int byTitleLength = this.bookService.getByTitleLength(value);
        System.out.printf("There are %d books with longer title than %d symbols\n", byTitleLength, value);
    }

    private void bookTitleSSearch() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        this.bookService.getByAuthorLastName(value).forEach(e -> System.out.printf("%s (%s %s)\n", e.getTitle(), e.getAuthor().getFirstName(), e.getAuthor().getLastName()));
    }

    private void bookSearch() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        this.bookService.getAllBooksContainingValueInTitle(value).forEach(e -> System.out.println(e.getTitle()));
    }

    private void authorsSearch() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        this.authorService.getAllWithNameEndingWith(value).forEach(e -> System.out.printf("%s %s\n", e.getFirstName(), e.getLastName()));
    }

    private void booksReleasedBeforeDate() throws IOException, ParseException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        this.bookService.booksReleasedBeforeDate(value).forEach(e -> System.out.println(e.getTitle()));
    }

    private void notReleasedBooks() throws IOException, ParseException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine().trim();
        this.bookService.getBooksNotReleasedIn(value).forEach(e -> System.out.println(e.getTitle()));
    }

    private void getByPriceInBoundaries()
    {
        this.bookService.getByPriceInBoundaries().forEach(e -> System.out.printf("%s - $%.2f\n", e.getTitle(), e.getPrice()));
    }

    private void goldenBooks()
    {
        this.bookService.getAllGoldenBooks().forEach(e ->
        {
            System.out.println(e.getTitle() + " " + e.getEditionType() + " " + e.getCopies());
        });
    }

    private void booksTitlesByAgeRestriction() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = br.readLine();
        this.bookService.getTitleByAgeRestriction(value).forEach(e -> System.out.println(e.getTitle()));
    }

}
