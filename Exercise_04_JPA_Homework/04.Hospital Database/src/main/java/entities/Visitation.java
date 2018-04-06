package entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "visitation")
public class Visitation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private Date date;
    @Basic
    private String comments;
    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    public Visitation()
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public Diagnose getDiagnose()
    {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose)
    {
        this.diagnose = diagnose;
    }

}
