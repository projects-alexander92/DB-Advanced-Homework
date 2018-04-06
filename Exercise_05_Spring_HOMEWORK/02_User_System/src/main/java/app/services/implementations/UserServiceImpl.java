package app.services.implementations;

import app.entities.User;
import app.repositories.UserRepository;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteByDateBefore(Date date)
    {
        this.userRepository.deleteByLastTimeLoggedInBefore(date);
    }

    @Override
    public Set<User> findByEmail(String email)
    {
        return this.userRepository.findByEmail(email);
    }


}
