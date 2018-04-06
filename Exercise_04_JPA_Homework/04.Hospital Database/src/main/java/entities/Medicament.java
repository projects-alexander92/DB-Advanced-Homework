package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "medicament")

public class Medicament
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String name;
    @ManyToMany(mappedBy = "medicament", targetEntity = Diagnose.class)
    private List<Diagnose> diagnoses;
    public Medicament()
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

    public List<Diagnose> getDiagnoses()
    {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnose> diagnoses)
    {
        this.diagnoses = diagnoses;
    }

}
