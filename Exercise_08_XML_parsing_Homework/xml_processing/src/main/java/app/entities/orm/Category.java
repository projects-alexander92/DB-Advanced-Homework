package app.entities.orm;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    @Size(min = 3, max = 15)
    private String name;
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Product> products;

    public Category()
    {
        this.products= new ArrayList<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Product> getProducts()
    {
        return this.products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
}
