package app.services.interfaces;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PhotographerService
{
    /*Import photographers and a random camera to each one of them*/
    @Transactional
    void importPhotographersFromJsonFile() throws IOException;

    void exportPhotographersToJson() throws IOException;

    @Transactional
    void photographerAndCamerasToXml() throws IOException, JAXBException;
}
