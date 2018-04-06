package enteties;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "wizzard_dposit")
public class WizzardDeposit
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 60)
    private String lastName;
    @Column
    private String notes;
    @Min(0)
    @Column
    private Integer age;
    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    @Min(1)
    @Max(32768)
    private Integer magicWandSize;
    @Column(name = "deposit_start_date")
    private Date depositStartDate;
    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;
    @Column(name = "deposit_interest")
    private BigDecimal depositInterest;
    @Column(name = "deposit_charge")
    private Float depositCharge;
    @Column(name = "deposit_expiration_date")
    private Date expirationDate;
    @Column(name = "is_deposit_expired")
    private Boolean isDepositExpired;

    public WizzardDeposit()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getMagicWandCreator()
    {
        return magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator)
    {
        this.magicWandCreator = magicWandCreator;
    }

    public Integer getMagicWandSize()
    {
        return magicWandSize;
    }

    public void setMagicWandSize(Integer magicWandSize)
    {
        this.magicWandSize = magicWandSize;
    }

    public Date getDepositStartDate()
    {
        return depositStartDate;
    }

    public void setDepositStartDate(Date depositStartDate)
    {
        this.depositStartDate = depositStartDate;
    }

    public BigDecimal getDepositAmount()
    {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount)
    {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getDepositInterest()
    {
        return depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest)
    {
        this.depositInterest = depositInterest;
    }

    public Float getDepositCharge()
    {
        return depositCharge;
    }

    public void setDepositCharge(Float depositCharge)
    {
        this.depositCharge = depositCharge;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public Boolean getDepositExpired()
    {
        return isDepositExpired;
    }

    public void setDepositExpired(Boolean depositExpired)
    {
        isDepositExpired = depositExpired;
    }
}
