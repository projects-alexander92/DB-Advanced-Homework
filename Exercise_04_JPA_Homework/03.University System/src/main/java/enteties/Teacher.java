package enteties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "teacher")
public class Teacher
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "salary_per_hour")
    private BigDecimal salaryPerHour;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courseSet;

    public Teacher()
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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public BigDecimal getSalaryPerHour()
    {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour)
    {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourseSet()
    {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet)
    {
        this.courseSet = courseSet;
    }
}
