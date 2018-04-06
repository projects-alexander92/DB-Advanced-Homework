package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "authors")
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> books;
    public Author()
    {
        this.books = new ArrayList<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Book> getBooks()
    {
        return this.books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
