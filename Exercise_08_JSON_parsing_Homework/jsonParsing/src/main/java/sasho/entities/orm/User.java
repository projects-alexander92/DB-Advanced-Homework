package sasho.entities.orm;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    @Size(min = 3)
    private String lastName;
    @Column
    private Integer age;
    @OneToMany(mappedBy = "seller")
    private Set<Product> soldProducts;
    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> users;

    public User()
    {
        this.users = new HashSet<>();
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

    public Set<User> getUsers()
    {
        return this.users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public Set<Product> getSoldProducts()
    {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts)
    {
        this.soldProducts = soldProducts;
    }
}
