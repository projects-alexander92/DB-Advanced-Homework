package app.repositories;

import app.entities.orm.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
