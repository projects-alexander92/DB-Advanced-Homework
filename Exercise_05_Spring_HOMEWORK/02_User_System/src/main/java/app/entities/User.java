package app.entities;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(min = 4, max = 30)
    private String username;
    @Column
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;
    @Column
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "registered_on")
    private Date registeredOn;
    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;
    @Column
    @Min(1)
    @Max(120)
    private Integer age;
    @Column
    private Boolean isDeleted;
    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @ManyToOne
    @JoinColumn(name = "born_town", referencedColumnName = "id")
    private Town bornTow;
    @ManyToOne
    @JoinColumn(name = "living_in_town", referencedColumnName = "id")
    private Town livingInTown;
    @ManyToMany
    @JoinTable(name = "user_albums",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private Set<Album> albums;
    public User()
    {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public byte[] getPicture()
    {
        return this.picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }

    public Date getRegisteredOn()
    {
        return this.registeredOn;
    }

    public void setRegisteredOn(Date registeredOn)
    {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn()
    {
        return this.lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn)
    {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge()
    {
        return this.age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Boolean getDeleted()
    {
        return this.isDeleted;
    }

    public void setDeleted(Boolean deleted)
    {
        isDeleted = deleted;
    }

    public Set<User> getFriends()
    {
        return this.friends;
    }

    public void setFriends(Set<User> friends)
    {
        this.friends = friends;
    }

    public Town getBornTow()
    {
        return this.bornTow;
    }

    public void setBornTow(Town bornTow)
    {
        this.bornTow = bornTow;
    }

    public Town getLivingInTown()
    {
        return this.livingInTown;
    }

    public void setLivingInTown(Town livingInTown)
    {
        this.livingInTown = livingInTown;
    }

    public Set<Album> getAlbums()
    {
        return this.albums;
    }

    public void setAlbums(Set<Album> albums)
    {
        this.albums = albums;
    }
}
