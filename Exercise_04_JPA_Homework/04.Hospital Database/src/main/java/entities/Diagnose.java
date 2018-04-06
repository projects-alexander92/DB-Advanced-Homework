package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "diagnose")
public class Diagnose
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;
    @Basic
    private String comments;
    @OneToOne(mappedBy = "diagnose")
    private Visitation visitation;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private List<Medicament> medicament;

    public Diagnose()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public List<Medicament> getMedicament()
    {
        return medicament;
    }

    public void setMedicament(List<Medicament> medicament)
    {
        this.medicament = medicament;
    }

    public Visitation getVisitation()
    {
        return visitation;
    }

    public void setVisitation(Visitation visitation)
    {
        this.visitation = visitation;
    }

}
