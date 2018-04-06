package app.services.interfaces;

import app.entities.orm.Category;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CategoryService
{
    void save(Category category);

    List<Category> getAll();

    @Transactional
    void getCategoriesByProductCount() throws IOException, JAXBException;
}
