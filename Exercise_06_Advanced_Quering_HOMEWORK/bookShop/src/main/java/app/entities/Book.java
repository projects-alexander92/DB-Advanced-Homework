package app.entities;

import app.entities.enums.AgeRestriction;
import app.entities.enums.EditionType;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;

@Entity(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "age_restriction")
    private Integer ageRestriction;
    @Column(nullable = false)
    private Integer copies;
    @Column
    @Size(max = 1000)
    private String description;
    @Column(nullable = false, name = "edition_type")
    private Integer editionType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    @Size(min = 1, max = 50)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auhtor_id", referencedColumnName = "id")
    private Author author;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "books_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id", referencedColumnName = "id"))
    private Set<Categorie> categories;

    public Book()
    {
        this.categories = new HashSet<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getAgeRestriction()
    {
        return this.ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction)
    {
        this.ageRestriction = ageRestriction.getValue();
    }

    public Integer getCopies()
    {
        return this.copies;
    }

    public void setCopies(Integer copies)
    {
        this.copies = copies;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getEditionType()
    {
        return this.editionType;
    }

    public void setEditionType(EditionType editionType)
    {
        this.editionType = editionType.getValue();
    }

    public BigDecimal getPrice()
    {
        return this.price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Date getReleaseDate()
    {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Author getAuthor()
    {
        return this.author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public Set<Categorie> getBooks()
    {
        return this.categories;
    }

    public void setBooks(Set<Categorie> books)
    {
        this.categories = books;
    }

    public Set<Categorie> getCategories()
    {
        return this.categories;
    }

    public void setCategories(Set<Categorie> categories)
    {
        this.categories = categories;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", ageRestriction=" + ageRestriction +
                ", copies=" + copies +
                ", description='" + description + '\'' +
                ", editionType=" + editionType +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}' + "\n";
    }
}
