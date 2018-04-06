package app.unitlities;

import app.config.FilePaths;
import app.entities.Author;
import app.entities.Book;
import app.entities.Categorie;
import app.entities.enums.AgeRestriction;
import app.entities.enums.EditionType;
import app.services.implementations.AuthorServiceImpl;
import app.services.implementations.BookServiceImpl;
import app.services.implementations.CategorieServiceImpl;
import app.services.interfaces.AuthorService;
import app.services.interfaces.BookService;
import app.services.interfaces.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DataParserImpl implements DataParser
{
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategorieService categorieService;

    @Autowired
    public DataParserImpl(AuthorService authorService, BookService bookService, CategorieService categorieService)
    {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categorieService = categorieService;
    }
    @Override
    @Transactional
    public void parseBooks() throws IOException, ParseException
    {
        List<Categorie> categories = this.categorieService.getAll();
        List<Author> authors = this.authorService.getAll();
        InputStream inputStream = getClass().getResourceAsStream(FilePaths.BOOKS_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        AgeRestriction[] ageRestrictions = AgeRestriction.values();
        EditionType[] editionTypes = EditionType.values();
        String currentLine;
        Book book;
        currentLine = br.readLine();
        while (currentLine != null)
        {
            book = new Book();

            String[] tokens = currentLine.split("\\s+");
            //age restriction
            int value;
            if ((int) tokens[0].charAt(0) == 65279)
            {
                value = 1;
            } else
            {
                value = Integer.parseInt(tokens[0]);
            }
            for (AgeRestriction ageRestriction : ageRestrictions)
            {
                if (ageRestriction.getValue() == value)
                {
                    book.setAgeRestriction(ageRestriction);
                    break;
                }
            }
            //set date
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = simpleDateFormat.parse(tokens[1]);
            book.setReleaseDate(date);
            //set copies
            book.setCopies(Integer.parseInt(tokens[2]));
            //set price
            book.setPrice(new BigDecimal(tokens[3]));

            //set edition type
            value = Integer.parseInt(tokens[4]);
            for (EditionType editionType : editionTypes)
            {
                if (editionType.getValue() == value)
                {
                    book.setEditionType(editionType);
                    break;
                }
            }
            //set title
            StringBuilder bookName = new StringBuilder();
            for (int i = 5; i < tokens.length - 1; i++)
            {
                bookName.append(tokens[i]).append(" ");
            }
            bookName.append(tokens[tokens.length - 1]);
            book.setTitle(bookName.toString());
            book = generateRandomAuthorAndCategorie(book, categories, authors);
            this.bookService.save(book);
            currentLine = br.readLine();
        }
    }

    private Book generateRandomAuthorAndCategorie(Book book, List<Categorie> categories, List<Author> authors)
    {
        long randomCategorieId = ThreadLocalRandom.current().nextLong(1, categories.size());
        long randomAuthorieId = ThreadLocalRandom.current().nextLong(1, authors.size());
        Author author = this.authorService.findById(randomAuthorieId);
        Categorie categorie = this.categorieService.findById(randomCategorieId);
        book.setAuthor(author);
        book.getCategories().add(categorie);
        return book;
    }

    @Override
    public void parseAuthors() throws IOException
    {
        InputStream inputStream = getClass().getResourceAsStream(FilePaths.AUTHORS_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String currentLine = br.readLine();
        String[] tokens;
        Author author;
        while (currentLine != null)
        {
            author = new Author();
            tokens = currentLine.split("\\s");
            author.setFirstName(tokens[0]);
            author.setLastName(tokens[1]);
            this.authorService.saveAuthor(author);
            currentLine = br.readLine();
        }
    }

    @Override
    public void parseCategories() throws IOException
    {
        InputStream inputStream = getClass().getResourceAsStream(FilePaths.CATEGORIES_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String currentLine = br.readLine();
        String[] tokens;
        Categorie categorie;
        while (currentLine != null)
        {
            categorie = new Categorie();
            tokens = currentLine.split("\\s");
            categorie.setName(tokens[0]);
            this.categorieService.saveCategorie(categorie);
            currentLine = br.readLine();
        }
    }

}
