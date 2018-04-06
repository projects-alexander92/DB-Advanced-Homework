package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "towns")
public class Town
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;

    @Basic
    private String country;

    public Town()
    {
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

    public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }
}
