package sasho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import sasho.config.*;
import sasho.services.interfaces.CategoryService;
import sasho.services.interfaces.ProductService;
import sasho.services.interfaces.UserService;

import java.io.*;


@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner
{
    private final CategoryService categoryService;
    private final PopulateDb populateDb;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ConsoleRunner(CategoryService categoryRepository, PopulateDb populateDb, UserService userService, ProductService productService)
    {
        this.categoryService = categoryRepository;
        this.populateDb = populateDb;
        this.userService = userService;
        this.productService = productService;
    }

    /*
    Различните задачи от това домашно са разеделени в различни методи в този клас
    */
    @Override
    public void run(String... args) throws Exception
    {
        //Problem 01 Seed the Database- ALWAYS RUN THIS METHOD AT LEAST ONES TO POPULATE THE DB!!!
        this.populateDb.generateAllRelations();
        //Problem 02 Query and Export Data
        //Query 1 - Products In Range
//        parseProductsInRangeToJson();
        //Query 2 - Successfully Sold Products
//        parseSuccessfullySoldProducts();
//        Query 3 - Categories By Products Count
//        categoriesByProductCount();
//        Query 4 - Users and Products
//        usersAndProducts();//file name to big exception, няма какво да направя защото не е от java този error

    }

    private void usersAndProducts() throws IOException
    {
        this.userService.getAllUsersAndProducts();
    }

    private void categoriesByProductCount() throws IOException
    {
        this.categoryService.getCategoriesByProductCount();
    }

    private void parseSuccessfullySoldProducts() throws IOException
    {
        this.userService.getAllUsersWithAblestOneSoldItem();
    }

    private void parseProductsInRangeToJson() throws IOException
    {
        this.productService.getProductsInPriceRange();
    }
}
