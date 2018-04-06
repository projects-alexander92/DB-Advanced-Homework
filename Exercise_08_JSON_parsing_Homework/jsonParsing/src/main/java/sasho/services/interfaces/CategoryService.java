package sasho.services.interfaces;

import sasho.entities.orm.Category;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface CategoryService
{
    void saveCategory(Category category);

    List<Category> getAll();

    @Transactional
    void getCategoriesByProductCount() throws IOException;
}
