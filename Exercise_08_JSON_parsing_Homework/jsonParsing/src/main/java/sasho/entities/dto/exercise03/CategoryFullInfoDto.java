package sasho.entities.dto.exercise03;

import com.google.gson.annotations.Expose;
import sasho.entities.orm.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CategoryFullInfoDto implements Serializable
{
    @Expose
    private String name;
    @Expose
    private int totalProductCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;
    private List<Product> products;
    public CategoryFullInfoDto()
    {
        this.products = new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getAveragePrice()
    {
        return this.averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice)
    {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue()
    {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue)
    {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalProductCount()
    {
        return this.totalProductCount;
    }

    public void setTotalProductCount(int totalProductCount)
    {
        this.totalProductCount = totalProductCount;
    }

    public List<Product> getProducts()
    {
        return this.products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
}
