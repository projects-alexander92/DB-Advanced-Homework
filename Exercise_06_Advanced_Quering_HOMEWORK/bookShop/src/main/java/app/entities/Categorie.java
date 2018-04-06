package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "categories")
public class Categorie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Book> books;

    public Categorie()
    {
        this.books= new HashSet<>();
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

    public Set<Book> getBooks()
    {
        return this.books;
    }

    public void setBooks(Set<Book> books)
    {
        this.books = books;
    }
}
