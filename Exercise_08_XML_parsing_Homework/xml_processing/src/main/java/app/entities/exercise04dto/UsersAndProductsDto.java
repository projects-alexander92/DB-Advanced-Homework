package app.entities.exercise04dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsDto
{
    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "user")
    private List<UserForExercise04Dto> userDtos;

    public UsersAndProductsDto()
    {
        this.userDtos = new ArrayList<>();
    }

    public List<UserForExercise04Dto> getUserDtos()
    {
        return this.userDtos;
    }

    public void setUserDtos(List<UserForExercise04Dto> userDtos)
    {
        this.userDtos = userDtos;
    }

    public int getCount()
    {
        return this.count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
}
