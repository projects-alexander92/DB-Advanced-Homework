package app.entities.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDto
{
    @XmlElement(name = "product")
    private List<ProductDto> productDtos;

    public ProductsDto()
    {
        this.productDtos = new ArrayList<>();
    }

    public List<ProductDto> getProductDtos()
    {
        return this.productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos)
    {
        this.productDtos = productDtos;
    }
}
