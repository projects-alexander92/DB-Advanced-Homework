package app.io.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlParser
{
    <T> T xmlToObject(Class<T> objectClass, String path) throws JAXBException;

    public <T> void objectToXml(T object, String file) throws IOException, JAXBException;
}
