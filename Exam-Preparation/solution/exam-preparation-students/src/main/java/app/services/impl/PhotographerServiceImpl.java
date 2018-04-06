package app.services.impl;

import app.config.FilePaths;
import app.enitties.jsonDto.ExportPhotographerToJson;
import app.enitties.orm.BasicCamera;
import app.enitties.orm.Photographer;
import app.enitties.jsonDto.PhotographerFromJsonDto;
import app.enitties.validation.EntitiesValidator;
import app.enitties.xmlDto.photographersToXml.PhotographerToXml;
import app.enitties.xmlDto.photographersToXml.PhotographersToXml;
import app.filereader.CustomFileManager;
import app.parsers.interfaces.JsonParser;
import app.parsers.interfaces.ModelParser;
import app.parsers.interfaces.XmlParser;
import app.repositories.CameraRepository;
import app.repositories.PhotographerRepository;
import app.services.interfaces.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class PhotographerServiceImpl implements PhotographerService
{
    @Autowired
    private PhotographerRepository photographerRepository;
    @Autowired
    private CustomFileManager customFileManager;
    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private EntitiesValidator entitiesValidator;
    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private XmlParser xmlParser;


    /*Import photographers and a random camera to each one of them*/
    @Override
    @Transactional
    public void importPhotographersFromJsonFile() throws IOException
    {
        //get the cameras
        List<BasicCamera> cameras = (List<BasicCamera>) cameraRepository.findAll();
        int camerasSize = cameras.size();
        String photographersJson = this.customFileManager.convertFileToString(FilePaths.PHOTOGRAPHERS_JSON);
        PhotographerFromJsonDto[] photographerFromJsonDtos = this.jsonParser.fromJsonToObject(PhotographerFromJsonDto[].class, photographersJson);
        for (PhotographerFromJsonDto photographerFromJsonDto : photographerFromJsonDtos)
        {
            Photographer photographer = this.modelParser.mapObject(photographerFromJsonDto, Photographer.class);
            //add the cameras
            int primaryCamera = new Random().nextInt(camerasSize);
            int secondaryCamera = new Random().nextInt(camerasSize);
            photographer.setSecondaryCamera(cameras.get(secondaryCamera));
            photographer.setPrimaryCamera(cameras.get(primaryCamera));

            if (validatePhotographer(photographer))
            {
                this.photographerRepository.save(photographer);
                System.out.println("Successfully imported " + photographer.getFirstName() + " " + photographer.getLastName());
            }
        }
    }

    private boolean validatePhotographer(Photographer photographer)
    {
        if (this.entitiesValidator.isValid(photographer))
        {
            return true;
        }
        System.out.println("Error. Invalid data provided");
        return false;
    }

    @Override
    public void exportPhotographersToJson() throws IOException
    {
        List<Photographer> photographers = this.photographerRepository.getAllOrderedByFirstName();
        ExportPhotographerToJson[] exportPhotographerToJsons = photographers.stream()
                .map(e -> this.modelParser.mapObject(e, ExportPhotographerToJson.class))
                .toArray(ExportPhotographerToJson[]::new);
        String json = this.jsonParser.fromObjectToJson(exportPhotographerToJsons);
        this.customFileManager.writeStringToFile(FilePaths.PHOTOGRAPHERS_ORDERED_JSON, json);
    }

    @Override
    @Transactional
    public void photographerAndCamerasToXml() throws IOException, JAXBException
    {
        List<Photographer> photographers = this.photographerRepository.getWithSamePrimaryAndSecondaryCameraMake();
        PhotographersToXml photographersToXml = new PhotographersToXml();
        for (Photographer photographer : photographers)
        {
            PhotographerToXml photographerToXml = new PhotographerToXml();
            photographerToXml.setFullName(photographer.getFirstName() + " " + photographer.getLastName());
            photographerToXml.setPrimaryCamera(photographer.getPrimaryCamera().getMake() + " " + photographer.getPrimaryCamera().getModel());
            photographersToXml.getPhotographers().add(photographerToXml);
        }
        this.xmlParser.objectToXml(photographersToXml, FilePaths.SAME_CAMERAS_PHOTOGRAPHERS_XML);
    }
}
