package app.entities.exercise02dto;

import app.entities.orm.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto
{
    @XmlElement(name = "user")
    private List<UserDto> usersDtos;

    public UsersDto()
    {
        this.usersDtos= new ArrayList<>();
    }

    public List<UserDto> getUsersDtos()
    {
        return this.usersDtos;
    }

    public void setUsersDtos(List<UserDto> usersDtos)
    {
        this.usersDtos = usersDtos;
    }
}
