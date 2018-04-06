package app.parsers.impl;

import app.parsers.interfaces.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class XmlParserImpl implements XmlParser
{
    private JAXBContext jaxbContext;

    @Override
    public <T> T xmlToObject(Class<T> objectClass, String path) throws JAXBException
    {
        this.jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();
        InputStream inputStream = getClass().getResourceAsStream(path);
        T object = (T) unmarshaller.unmarshal(inputStream);
        return object;
    }

    @Override
    public <T> void objectToXml(T object, String file) throws IOException, JAXBException
    {
        this.jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = this.jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        OutputStream outputStream = new FileOutputStream(file);
        marshaller.marshal(object, outputStream);
    }
}
