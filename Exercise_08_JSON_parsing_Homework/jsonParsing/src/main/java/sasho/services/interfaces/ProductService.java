package sasho.services.interfaces;

import sasho.entities.orm.Product;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService
{
    List<Product> getAll();

    void save(Product product);

    void getProductsInPriceRange() throws IOException;
}
