package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "categories")
public class Categorie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;

    public Categorie()
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
}
