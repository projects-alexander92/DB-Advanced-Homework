package app.entities.exercise04dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserForExercise04Dto
{
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private int age;
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ProductForExercise04Dto> productForExercise04Dtos;

    public UserForExercise04Dto()
    {
        this.productForExercise04Dtos = new ArrayList<>();
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public List<ProductForExercise04Dto> getProductForExercise04Dtos()
    {
        return this.productForExercise04Dtos;
    }

    public void setProductForExercise04Dtos(List<ProductForExercise04Dto> productForExercise04Dtos)
    {
        this.productForExercise04Dtos = productForExercise04Dtos;
    }
}
