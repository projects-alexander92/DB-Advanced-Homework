package app.services.impl;

import app.config.FilePaths;
import app.entities.exercise02dto.UserDto;
import app.entities.exercise02dto.UsersDto;
import app.entities.exercise04dto.ProductForExercise04Dto;
import app.entities.exercise04dto.UserForExercise04Dto;
import app.entities.exercise04dto.UsersAndProductsDto;
import app.entities.orm.Product;
import app.entities.orm.User;
import app.io.interfaces.ModelParser;
import app.io.interfaces.XmlParser;
import app.repositories.UserRepository;
import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private XmlParser xmlParser;

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
    public void getAllUsersWithAblestOneSoldItem() throws IOException, JAXBException
    {
        UsersDto usersDto = new UsersDto();
        Set<User> users = this.userRepository.getAllUsersWithAblestOneSoldItem();
        users.forEach(e ->
        {
            UserDto userDto = this.modelParser.parse(e, UserDto.class);
            usersDto.getUsersDtos().add(userDto);
        });
        this.xmlParser.objectToXml(usersDto, FilePaths.SUCCESSFULLY_SOLD_PRODUCTS);
    }

    @Override
    @Transactional
    public void getAllUsersAndProducts() throws IOException, JAXBException
    {
        List<User> users = this.userRepository.getAllUsers();
        UsersAndProductsDto usersAndProductsDto = new UsersAndProductsDto();
        for (User user : users)
        {
            UserForExercise04Dto userForExercise04Dto = this.modelParser.parse(user, UserForExercise04Dto.class);
            for (Product product : user.getSoldProducts())
            {
                ProductForExercise04Dto productForExercise04Dto = this.modelParser.parse(product, ProductForExercise04Dto.class);
                userForExercise04Dto.getProductForExercise04Dtos().add(productForExercise04Dto);
            }
            usersAndProductsDto.getUserDtos().add(userForExercise04Dto);
        }
        usersAndProductsDto.setCount(users.size());
        this.xmlParser.objectToXml(usersAndProductsDto, FilePaths.USERS_AND_PRODUCTS);
    }
}
