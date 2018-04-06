package sasho.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasho.config.FilePaths;
import sasho.entities.dto.exercise02.FullUserInfoDto;
import sasho.entities.dto.exercise04.UserArrayDto;
import sasho.entities.orm.User;
import sasho.io.interfaces.CustomFileManager;
import sasho.io.interfaces.JsonParser;
import sasho.io.interfaces.ModelParser;
import sasho.repositories.UserRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements sasho.services.interfaces.UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private CustomFileManager customFileManager;
    @Autowired
    private ModelParser modelParser;

    @Override
    public List<User> getAll()
    {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public void save(User user)
    {
        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void getAllUsersWithAblestOneSoldItem() throws IOException
    {
        Set<User> users = this.userRepository.getAllUsersWithAblestOneSoldItem();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User user : users)
        {
            FullUserInfoDto fullUserInfoDto = this.modelParser.parse(user, FullUserInfoDto.class);
            sb.append(jsonParser.write(fullUserInfoDto));
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        this.customFileManager.writeStringToFile(FilePaths.SUCCESSFULLY_SOLD_PRODUCTS, sb.toString());
    }

    @Override
    @Transactional
    public void getAllUsersAndProducts() throws IOException
    {
        List<User> users = this.userRepository.getAllUsers();
        UserArrayDto userArrayDto = new UserArrayDto();
        userArrayDto.setUsersCount(users.size());
        for (User user : users)
        {
            FullUserInfoDto fullUserInfoDto = this.modelParser.parse(user, FullUserInfoDto.class);
            userArrayDto.getUsers().add(fullUserInfoDto);
        }
        String json = this.jsonParser.write(userArrayDto);
        this.customFileManager.writeStringToFile(json, FilePaths.USERS_AND_PRODUCTS);

    }
}
