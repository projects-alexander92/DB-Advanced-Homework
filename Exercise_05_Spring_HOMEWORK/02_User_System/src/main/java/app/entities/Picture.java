package app.entities;

import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;


@Entity(name = "pictures")
public class Picture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Lob
    @Column(name = "picture")
    private byte[] caption;
    @Column
    private String path;
    @ManyToMany
    @JoinTable(name = "picture_albums",
            joinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"))
    private Set<Album> albums;

    public Picture()
    {
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

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public byte[] getCaption()
    {
        return this.caption;
    }

    public void setCaption(byte[] caption)
    {
        this.caption = caption;
    }

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
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
