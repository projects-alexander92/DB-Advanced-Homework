package app.repositories;

import app.enitties.orm.Photographer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, Long>
{
    Photographer findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select p from Photographer as p order by p.firstName asc , p.lastName desc")
    List<Photographer> getAllOrderedByFirstName();

    @Query("select p from Photographer as p where p.primaryCamera.make = p.secondaryCamera.make")
    List<Photographer> getWithSamePrimaryAndSecondaryCameraMake();
}
