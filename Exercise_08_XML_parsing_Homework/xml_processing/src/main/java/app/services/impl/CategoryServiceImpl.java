package app.services.impl;

import app.config.FilePaths;
import app.entities.exercise03dto.CategoriesDto;
import app.entities.exercise03dto.CategoryDto;
import app.entities.orm.Category;
import app.entities.orm.Product;
import app.io.interfaces.ModelParser;
import app.io.interfaces.XmlParser;
import app.repositories.CategoryRepository;
import app.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private XmlParser xmlParser;

    @Override
    public void save(Category category)
    {
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll()
    {
        return this.categoryRepository.getAll();
    }

    @Override
    @Transactional
    public void getCategoriesByProductCount() throws JAXBException, IOException
    {
        Set<Category> categories = this.categoryRepository.getAllCategoriesOrderByProductCount();
        CategoriesDto categoriesDto = new CategoriesDto();
        for (Category category : categories)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            BigDecimal totalValue = new BigDecimal("0");
            BigDecimal averageValue = new BigDecimal("0");
            for (Product product : category.getProducts())
            {
                totalValue = totalValue.add(product.getPrice());
            }
            averageValue = totalValue.divide(new BigDecimal(category.getProducts().size()), 2);
            categoryDto.setAvaragePrice(averageValue);
            categoryDto.setTotalRevenue(totalValue);
            categoryDto.setProductsCount(category.getProducts().size());
            categoriesDto.getCategoryDtos().add(categoryDto);
        }
        this.xmlParser.objectToXml(categoriesDto, FilePaths.CATEGORIES_BY_PRODUCTS);
    }
}
