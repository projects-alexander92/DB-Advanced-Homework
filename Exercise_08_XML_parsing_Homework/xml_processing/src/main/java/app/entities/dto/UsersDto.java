package app.entities.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersDto
{
    @XmlElement(name = "user")
    List<UserDto> userDtos;

    public UsersDto()
    {
        this.userDtos = new ArrayList<>();
    }

    public List<UserDto> getUserDtos()
    {
        return this.userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos)
    {
        this.userDtos = userDtos;
    }
}
