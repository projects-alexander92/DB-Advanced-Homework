package enteties;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "customer")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;
    @Basic
    private String email;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;

    public Customer()
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCreditCardNumber()
    {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber)
    {
        this.creditCardNumber = creditCardNumber;
    }

    public Set<Sale> getSales()
    {
        return sales;
    }

    public void setSales(Set<Sale> salaes)
    {
        this.sales = salaes;
    }
}
