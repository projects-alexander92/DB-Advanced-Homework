package sasho.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasho.config.FilePaths;
import sasho.entities.dto.exercise03.CategoryFullInfoDto;
import sasho.entities.orm.Category;
import sasho.entities.orm.Product;
import sasho.io.interfaces.CustomFileManager;
import sasho.io.interfaces.JsonParser;
import sasho.io.interfaces.ModelParser;
import sasho.repositories.CategoryRepository;
import sasho.services.interfaces.CategoryService;

import javax.transaction.Transactional;
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
    private JsonParser jsonParser;
    @Autowired
    private CustomFileManager customFileManager;

    @Override
    public void saveCategory(Category category)
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
    public void getCategoriesByProductCount() throws IOException
    {
        Set<Category> categories = this.categoryRepository.getAllCategoriesOrderByProductCount();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Category category : categories)
        {
            CategoryFullInfoDto categoryFullInfoDto = this.modelParser.parse(category, CategoryFullInfoDto.class);
            BigDecimal totalValue = new BigDecimal("0");
            BigDecimal averageValue = new BigDecimal("0");
            for (Product product : category.getProducts())
            {
                totalValue = totalValue.add(product.getPrice());
            }
            averageValue = totalValue.divide(new BigDecimal(category.getProducts().size()), 2);
            categoryFullInfoDto.setAveragePrice(averageValue);
            categoryFullInfoDto.setTotalRevenue(totalValue);
            categoryFullInfoDto.setTotalProductCount(category.getProducts().size());
            String json = this.jsonParser.write(categoryFullInfoDto);
            sb.append(json);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");

        this.customFileManager.writeStringToFile(FilePaths.CATEGORIES_BY_PRODUCTS, sb.toString());
    }
}
