package sasho.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sasho.entities.orm.Category;
import sasho.entities.orm.Product;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>
{
    @Query("select p from products p where p.price >= 500 and p.price <= 1000")
    Set<Product> getProductsInPriceRange();

    @Query("select p from  products as p")
    List<Product> getAll();

}
