package app.repositories;

import app.entities.orm.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    @Query("select u from users as u where u.soldProducts.size >=1 order by u.lastName, u.firstName")
    Set<User> getAllUsersWithAblestOneSoldItem();

    @Query("select u from users as u")
    List<User> getAllUsers();

}
