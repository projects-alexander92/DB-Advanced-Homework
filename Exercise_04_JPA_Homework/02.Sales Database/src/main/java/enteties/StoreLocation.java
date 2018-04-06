package enteties;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "store_location ")
public class StoreLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "location_name")
    private String locationName;
    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
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
