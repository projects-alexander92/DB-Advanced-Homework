package app.enitties.xmlDto.workshopFromXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopsFromXml
{
    @XmlElement(name = "workshop")
    private List<WorkshopFromXml> workshops;

    public WorkshopsFromXml()
    {
        this.workshops = new ArrayList<>();
    }

    public List<WorkshopFromXml> getWorkshops()
    {
        return this.workshops;
    }

    public void setWorkshops(List<WorkshopFromXml> workshops)
    {
        this.workshops = workshops;
    }
}
