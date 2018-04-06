package app.services.interfaces;

import app.entities.orm.Product;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService
{
    List<Product> getAll();

    void save(Product product);

    void getProductsInPriceRange() throws IOException, JAXBException;
}
