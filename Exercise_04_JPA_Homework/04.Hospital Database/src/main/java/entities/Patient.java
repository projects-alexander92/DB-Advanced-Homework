package entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "patient")
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firsName;
    @Column(name = "last_name")
    private String lastName;
    @Basic
    private String adress;
    @Basic
    private String email;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column
    @Lob
    private byte[] picture;
    @Column(name = "medical_insurance")
    private Boolean medicalInsurance;
    @OneToOne(mappedBy = "patient")
    private Visitation visitation;
    public Patient()
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

    public String getFirsName()
    {
        return firsName;
    }

    public void setFirsName(String firsName)
    {
        this.firsName = firsName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getAdress()
    {
        return adress;
    }

    public void setAdress(String adress)
    {
        this.adress = adress;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }

    public Boolean getMedicalInsurance()
    {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Boolean medicalInsurance)
    {
        this.medicalInsurance = medicalInsurance;
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
