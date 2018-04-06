package app.enitties.xmlDto.photographersToXml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographersToXml
{
    @XmlElement(name = "photographer")
    private List<PhotographerToXml> photographers;

    public PhotographersToXml()
    {
        this.photographers = new ArrayList<>();
    }

    public List<PhotographerToXml> getPhotographers()
    {
        return this.photographers;
    }

    public void setPhotographers(List<PhotographerToXml> photographers)
    {
        this.photographers = photographers;
    }
}
