package app.parsers.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface XmlParser
{
    <T> T xmlToObject(Class<T> objectClass, String path) throws JAXBException;

    <T> void objectToXml(T object, String file) throws IOException, JAXBException;
}
