package app.enitties.xmlDto.accessoriessFromXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "accessory")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoryFromXml
{
    @XmlAttribute(name = "name")
    private String name;

    public AccessoryFromXml()
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
}
