package app.enitties.xmlDto.workshopFromXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "participant")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerFromXml
{
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;

    public PhotographerFromXml()
    {
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
}
