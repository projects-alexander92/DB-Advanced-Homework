package enteties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @Column
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;

    public Product()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Set<Sale> getSales()
    {
        return sales;
    }

    public void setSales(Set<Sale> sales)
    {
        this.sales = sales;
    }
}
