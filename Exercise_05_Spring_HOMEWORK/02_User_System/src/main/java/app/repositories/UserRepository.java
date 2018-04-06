package app.repositories;

import app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Set<User> findByEmail(String email);

    void deleteByLastTimeLoggedInBefore(Date date);
}
