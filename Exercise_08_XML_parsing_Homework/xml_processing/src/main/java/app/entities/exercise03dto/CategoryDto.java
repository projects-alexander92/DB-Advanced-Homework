package app.entities.exercise03dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDto
{
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "products-count")
    private int productsCount;
    @XmlElement(name = "average-price")
    private BigDecimal avaragePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryDto()
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

    public int getProductsCount()
    {
        return this.productsCount;
    }

    public void setProductsCount(int productsCount)
    {
        this.productsCount = productsCount;
    }

    public BigDecimal getAvaragePrice()
    {
        return this.avaragePrice;
    }

    public void setAvaragePrice(BigDecimal avaragePrice)
    {
        this.avaragePrice = avaragePrice;
    }

    public BigDecimal getTotalRevenue()
    {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue)
    {
        this.totalRevenue = totalRevenue;
    }
}
