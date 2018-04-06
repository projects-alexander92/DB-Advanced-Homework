package app;

import app.config.PopulateDb;
import app.services.interfaces.CategoryService;
import app.services.interfaces.ProductService;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner
{
    private final PopulateDb populateDb;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ConsoleRunner(PopulateDb populateDb, UserService userService, CategoryService categoryService, ProductService productService)
    {
        this.populateDb = populateDb;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }
    /*Различните задачи от това упражнение съм ги отделил в различни методи*/
    @Override
    public void run(String... args) throws Exception
    {
        //Seed the Database
        this.populateDb.generateAllRelations();
        //Query 1 - Products In Range
        parseProductsInRangeToXml();
        //Query 2 - Successfully Sold Products
        successfullySoldProducts();
        //Query 3 - Categories By Products Count
        categoriesByProductCount();
        //Query 4 - Users and Products
        usersAndProducts();

    }

    private void successfullySoldProducts() throws IOException, JAXBException
    {
        this.userService.getAllUsersWithAblestOneSoldItem();
    }

    private void parseProductsInRangeToXml() throws IOException, JAXBException
    {
        this.productService.getProductsInPriceRange();
    }

    private void categoriesByProductCount() throws IOException, JAXBException
    {
        this.categoryService.getCategoriesByProductCount();
    }

    private void usersAndProducts() throws IOException, JAXBException
    {
        this.userService.getAllUsersAndProducts();
    }

}
