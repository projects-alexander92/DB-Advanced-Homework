package sasho.entities.orm;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(min = 3)
    private String name;
    @Column
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categorie_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    public Product()
    {
        this.categories = new ArrayList<>();
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

    public BigDecimal getPrice()
    {
        return this.price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public User getSeller()
    {
        return this.seller;
    }

    public void setSeller(User seller)
    {
        this.seller = seller;
    }

    public User getBuyer()
    {
        return this.buyer;
    }

    public void setBuyer(User buyer)
    {
        this.buyer = buyer;
    }

    public List<Category> getCategories()
    {
        return this.categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }
}
