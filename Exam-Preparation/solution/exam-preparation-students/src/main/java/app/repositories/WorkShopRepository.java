package app.repositories;

import app.enitties.orm.Workshop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShopRepository extends CrudRepository<Workshop, Long>
{
}
