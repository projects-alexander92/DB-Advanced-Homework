package app.services.interfaces;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

public interface WorkshopService
{
    @Transactional
    void importWorkshopsFromXmlFile() throws JAXBException;
}
