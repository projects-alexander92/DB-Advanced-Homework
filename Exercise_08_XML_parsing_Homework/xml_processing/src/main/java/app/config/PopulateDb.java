package app.config;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PopulateDb
{
    void generateAllRelations() throws IOException, JAXBException;
}
