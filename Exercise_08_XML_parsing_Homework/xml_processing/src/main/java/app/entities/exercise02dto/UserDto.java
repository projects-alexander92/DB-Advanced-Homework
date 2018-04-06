package app.entities.exercise02dto;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto
{
    @XmlAttribute(name = "name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<ProductDto> soldProducts;

    public UserDto()
    {
        this.soldProducts = new HashSet<>();
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

    public Set<ProductDto> getSoldProducts()
    {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<ProductDto> soldProducts)
    {
        this.soldProducts = soldProducts;
    }
}
