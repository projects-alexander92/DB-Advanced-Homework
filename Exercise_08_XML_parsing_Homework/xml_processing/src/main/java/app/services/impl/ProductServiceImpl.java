package app.services.impl;

import app.config.FilePaths;
import app.entities.dto.ProductDto;
import app.entities.dto.ProductsDto;
import app.entities.orm.Product;
import app.io.interfaces.ModelParser;
import app.io.interfaces.XmlParser;
import app.repositories.ProductRepository;
import app.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private XmlParser xmlParser;

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
    public void getProductsInPriceRange() throws IOException, JAXBException
    {
        ProductsDto productsDto = new ProductsDto();
        Set<Product> productsInPriceRange = this.productRepository.getProductsInPriceRange();
        productsInPriceRange.forEach(e ->
        {
            ProductDto productDto = this.modelParser.parse(e, ProductDto.class);
            productsDto.getProductDtos().add(productDto);
        });
        this.xmlParser.objectToXml(productsDto, FilePaths.PRODUCTS_IN_RANGE);
    }
}
