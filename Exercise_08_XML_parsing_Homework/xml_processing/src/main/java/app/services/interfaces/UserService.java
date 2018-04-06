package app.services.interfaces;

import app.entities.orm.User;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService
{
    List<User> getAll();

    void save(User user);

    @Transactional
    void getAllUsersWithAblestOneSoldItem() throws IOException, JAXBException;

    @Transactional
    void getAllUsersAndProducts() throws IOException, JAXBException;
}
