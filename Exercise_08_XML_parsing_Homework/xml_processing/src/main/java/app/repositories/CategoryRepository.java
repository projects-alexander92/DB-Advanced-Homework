package app.repositories;

import app.entities.orm.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>
{
    @Query("select c from categories as c")
    List<Category> getAll();

    Category findById(long id);

    @Query("select c from categories as c order by c.products.size")
    Set<Category> getAllCategoriesOrderByProductCount();
}
