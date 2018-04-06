package sasho.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sasho.entities.dto.exercise01.CategoryDto;
import sasho.entities.dto.exercise01.ProductDto;
import sasho.entities.dto.exercise01.UserDto;
import sasho.entities.orm.Category;
import sasho.entities.orm.Product;
import sasho.entities.orm.User;
import sasho.io.interfaces.CustomFileManager;
import sasho.io.interfaces.JsonParser;
import sasho.io.interfaces.ModelParser;
import sasho.services.interfaces.CategoryService;
import sasho.services.interfaces.ProductService;
import sasho.services.interfaces.UserService;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private final JsonParser jsonParser;
    private final CustomFileManager customFileReader;

    @Autowired
    public PopulateDbImpl(UserService userService, ProductService productService, CategoryService categoryService, ModelParser modelParser, JsonParser jsonParser, CustomFileManager customFileReader)
    {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelParser = modelParser;
        this.jsonParser = jsonParser;
        this.customFileReader = customFileReader;
    }

    @Override
    public void generateAllRelations() throws IOException
    {
        parseDataFromJsonFiles();
        //generate random foreign key relations
        connectProductsToCategories();
        connectUsersToProducts();
    }

    private void parseDataFromJsonFiles() throws IOException
    {
        //parse users
        String file = customFileReader.convertFileToString(FilePaths.USERS_JSON);
        UserDto[] userDtos = this.jsonParser.fromJsonToObject(UserDto[].class, file);
        for (UserDto dto : userDtos)
        {

            User user = this.modelParser.parse(dto, User.class);
            this.userService.save(user);
        }

        //parse products
        file = customFileReader.convertFileToString(FilePaths.PRODUCTS_JSON);
        ProductDto[] productDtos = this.jsonParser.fromJsonToObject(ProductDto[].class, file);
        for (ProductDto productDto : productDtos)
        {
            Product product = this.modelParser.parse(productDto, Product.class);
            this.productService.save(product);
        }

        //parse
        file = customFileReader.convertFileToString(FilePaths.CATEGORIES_JSON);
        CategoryDto[] categoryDtos = this.jsonParser.fromJsonToObject(CategoryDto[].class, file);
        for (CategoryDto categoryDto : categoryDtos)
        {
            Category category = this.modelParser.parse(categoryDto, Category.class);
            this.categoryService.saveCategory(category);
        }
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
