package sasho.entities.dto.exercise02;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class FullProductInfoDto implements Serializable
{
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
    private String buyerLastName;
    public FullProductInfoDto()
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
