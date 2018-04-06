package sasho.entities.dto.exercise02;

import com.google.gson.annotations.Expose;
import sasho.entities.orm.Product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FullUserInfoDto implements Serializable
{
    @Expose
    private Long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private Set<FullProductInfoDto> soldProducts;

    public FullUserInfoDto()
    {
        this.soldProducts = new HashSet<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public Integer getAge()
    {
        return this.age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Set<FullProductInfoDto> getSoldProducts()
    {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<FullProductInfoDto> soldProducts)
    {
        this.soldProducts = soldProducts;
    }
}
