package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Basic
    private String email;
    @Basic
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<BillingDetail> billingDetails;

    public User()
    {
    }
}