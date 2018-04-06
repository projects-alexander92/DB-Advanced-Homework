package app.repositories;

import app.enitties.orm.Accessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends CrudRepository<Accessory, Long>
{

}
