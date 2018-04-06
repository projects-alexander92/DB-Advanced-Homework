package app.repositories;

import app.entities.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long>
{
}
