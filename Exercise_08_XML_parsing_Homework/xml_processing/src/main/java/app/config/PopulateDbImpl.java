package app.config;

import app.entities.dto.CategoriesDto;
import app.entities.dto.ProductsDto;
import app.entities.dto.UsersDto;
import app.entities.orm.Category;
import app.entities.orm.Product;
import app.entities.orm.User;
import app.io.interfaces.ModelParser;
import app.io.interfaces.XmlParser;
import app.services.interfaces.CategoryService;
import app.services.interfaces.ProductService;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Transactional
public class PopulateDbImpl implements PopulateDb
{
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelParser modelParser;
    private final XmlParser xmlParser;

    @Autowired
    public PopulateDbImpl(UserService userService, ProductService productService, CategoryService categoryService, ModelParser modelParser, XmlParser xmlParser)
    {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelParser = modelParser;
        this.xmlParser = xmlParser;
    }

    @Override
    public void generateAllRelations() throws JAXBException
    {
        parseDataFromJsonFiles();
        connectProductsToCategories();
        connectUsersToProducts();
    }

    private void parseDataFromJsonFiles() throws JAXBException
    {
        UsersDto userDto = this.xmlParser.xmlToObject(UsersDto.class, FilePaths.USERS_XML);
        userDto.getUserDtos().forEach(e ->
        {
            User user = this.modelParser.parse(e, User.class);
            this.userService.save(user);
        });

        CategoriesDto categoriesDto = this.xmlParser.xmlToObject(CategoriesDto.class, FilePaths.CATEGORIES_XML);
        categoriesDto.getCategoryDtos().forEach(e ->
        {
            Category category = this.modelParser.parse(e, Category.class);
            this.categoryService.save(category);
        });

        ProductsDto productsDto = this.xmlParser.xmlToObject(ProductsDto.class, FilePaths.PRODUCTS_XML);
        productsDto.getProductDtos().forEach(e ->
        {
            Product product = this.modelParser.parse(e, Product.class);
            this.productService.save(product);
        });
    }

    private void connectUsersToProducts()
    {
        List<User> users = this.userService.getAll();
        List<Product> products = this.productService.getAll();
        for (Product product : products)
        {
            //Buyer and owner can be the same person
            int randomUser = new Random().nextInt(users.size());
            User randomOwner = users.get(randomUser);
            product.setSeller(randomOwner);
            randomUser = new Random().nextInt(users.size());
            User randomBuyer = users.get(randomUser);
            product.setBuyer(randomBuyer);
            this.productService.save(product);
        }

    }

    private void connectProductsToCategories()
    {
        List<Product> products = this.productService.getAll();
        List<Category> categories = this.categoryService.getAll();
        for (Product product : products)
        {
            int numberOfCategories = new Random().nextInt(categories.size());
            for (int i = 0; i < numberOfCategories; i++)
            {
                long randomCategory = new Random().nextInt(categories.size());
                product.getCategories().add(categories.get((int) randomCategory));
            }
            //Нямаше как с HashSet да стане защото ми трябва по индекс после да взимам
            List<Category> distinct = product.getCategories().stream().distinct().collect(Collectors.toList());
            product.setCategories(distinct);
            this.productService.save(product);
        }
    }
}
