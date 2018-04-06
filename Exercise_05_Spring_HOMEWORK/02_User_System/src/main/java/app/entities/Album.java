package app.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "albums")
public class Album
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String backgroundColor;
    @Column
    private Boolean isPublic;
    @ManyToMany(mappedBy = "albums")
    private Set<Picture> pictures;
    public Album()
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

    public String getBackgroundColor()
    {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getPublic()
    {
        return this.isPublic;
    }

    public void setPublic(Boolean aPublic)
    {
        isPublic = aPublic;
    }
}
