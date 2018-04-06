package app.entities.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesDto
{
    @XmlElement(name = "category")
    private List<CategoryDto> categoryDtos;

    public CategoriesDto()
    {
        this.categoryDtos = new ArrayList<>();
    }

    public List<CategoryDto> getCategoryDtos()
    {
        return this.categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos)
    {
        this.categoryDtos = categoryDtos;
    }
}
