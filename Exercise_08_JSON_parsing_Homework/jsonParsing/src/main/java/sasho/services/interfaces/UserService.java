package sasho.services.interfaces;

import sasho.entities.orm.User;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public interface UserService
{
    List<User> getAll();

    void save(User user);

    @Transactional
    void getAllUsersWithAblestOneSoldItem() throws IOException;

    @Transactional
    void getAllUsersAndProducts() throws IOException;
}
