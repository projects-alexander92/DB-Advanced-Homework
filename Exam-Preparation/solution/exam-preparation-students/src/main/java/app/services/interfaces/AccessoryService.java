package app.services.interfaces;

import javax.xml.bind.JAXBException;

public interface AccessoryService
{
    void importAccessoriesFromXmlFile() throws JAXBException;
}
