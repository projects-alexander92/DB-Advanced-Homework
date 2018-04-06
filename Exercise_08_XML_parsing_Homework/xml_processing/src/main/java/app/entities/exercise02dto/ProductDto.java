package app.entities.exercise02dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto
{
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "buyer-first-name")
    private String buyerFirstName;
    @XmlElement(name = "buyer-last-name")
    private String buyerLastName;

    public ProductDto()
    {
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return this.price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public String getBuyerFirstName()
    {
        return this.buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName)
    {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName()
    {
        return this.buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName)
    {
        this.buyerLastName = buyerLastName;
    }
}
