package app.enitties.xmlDto.accessoriessFromXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoriesFromXml
{
    @XmlElement(name = "accessory")
    private List<AccessoryFromXml> accessories;

    public AccessoriesFromXml()
    {
        this.accessories = new ArrayList<>();
    }

    public List<AccessoryFromXml> getAccessories()
    {
        return this.accessories;
    }

    public void setAccessories(List<AccessoryFromXml> accessories)
    {
        this.accessories = accessories;
    }
}
