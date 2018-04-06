package app.services.impl;

import app.config.FilePaths;
import app.enitties.orm.Accessory;
import app.enitties.xmlDto.accessoriessFromXml.AccessoriesFromXml;
import app.parsers.interfaces.ModelParser;
import app.parsers.interfaces.XmlParser;
import app.repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class AccessoryServiceImpl implements app.services.interfaces.AccessoryService
{
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private XmlParser xmlParser;

    @Override
    public void importAccessoriesFromXmlFile() throws JAXBException
    {
        AccessoriesFromXml accessoriesFromXml = this.xmlParser.xmlToObject(AccessoriesFromXml.class, FilePaths.ACCESSORIES_XML);
        accessoriesFromXml.getAccessories().forEach(e ->
        {
            Accessory accessory = this.modelParser.mapObject(e, Accessory.class);
            this.accessoryRepository.save(accessory);
            System.out.println("Successfully imported " + accessory.getName());
        });

    }
}
