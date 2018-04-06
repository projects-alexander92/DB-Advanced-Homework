package sasho.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasho.config.FilePaths;
import sasho.entities.dto.exercise01.ProductDto;
import sasho.entities.orm.Product;
import sasho.io.interfaces.CustomFileManager;
import sasho.io.interfaces.JsonParser;
import sasho.io.interfaces.ModelParser;
import sasho.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements sasho.services.interfaces.ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private CustomFileManager customFileManager;

    @Override
    public List<Product> getAll()
    {
        return this.productRepository.getAll();
    }

    @Override
    public void save(Product product)
    {
        this.productRepository.save(product);
    }

    @Override
    public void getProductsInPriceRange() throws IOException
    {
        Set<Product> productsInPriceRange = this.productRepository.getProductsInPriceRange();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Product product : productsInPriceRange)
        {
            ProductDto productDto = this.modelParser.parse(product, ProductDto.class);
            sb.append(this.jsonParser.write(productDto)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        this.customFileManager.writeStringToFile(FilePaths.PRODUCTS_IN_RANGE, sb.toString());
    }
}
