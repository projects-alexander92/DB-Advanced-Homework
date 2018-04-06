package app.services.interfaces;

import app.entities.User;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

public interface UserService
{
    @Transactional
    @Modifying
    void deleteByDateBefore(Date date);

    Set<User> findByEmail(String email);
}
