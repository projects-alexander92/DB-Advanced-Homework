package enteties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sale")
public class Sale
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column
    private Date date;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;

    public StoreLocation getStoreLocation()
    {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation)
    {
        this.storeLocation = storeLocation;
    }

    public Sale()
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

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}
