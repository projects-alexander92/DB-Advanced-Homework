package app.enitties.xmlDto.photographersToXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "photographer")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerToXml
{
    @XmlAttribute(name = "name")
    private String fullName;
    @XmlAttribute(name = "primary-camera")
    private String primaryCamera;

    public PhotographerToXml()
    {
    }

    public String getFullName()
    {
        return this.fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getPrimaryCamera()
    {
        return this.primaryCamera;
    }

    public void setPrimaryCamera(String primaryCamera)
    {
        this.primaryCamera = primaryCamera;
    }
}
