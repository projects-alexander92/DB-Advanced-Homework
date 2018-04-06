package entities;


import javax.persistence.*;

@Entity(name = "billing_detail")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail
{
    @Id
    private String number;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    public BillingDetail()
    {
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }
}
