package app.services.impl;

import app.config.FilePaths;
import app.enitties.orm.BasicCamera;
import app.enitties.orm.DSLRCamera;
import app.enitties.orm.MirrorlessCamera;
import app.enitties.jsonDto.CameraFromJsonDto;
import app.enitties.validation.EntitiesValidator;
import app.filereader.CustomFileManager;
import app.parsers.interfaces.JsonParser;
import app.parsers.interfaces.ModelParser;
import app.repositories.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CameraServiceImpl implements app.services.interfaces.CameraService
{
    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private CustomFileManager customFileManager;
    @Autowired
    private JsonParser jsonParser;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private EntitiesValidator entitiesValidator;

    @Override
    public void importCamerasFromJsonFile() throws IOException
    {
        String camerasJson = customFileManager.convertFileToString(FilePaths.CAMERAS_JSON);
        CameraFromJsonDto[] cameraFromJsonDtos = this.jsonParser.fromJsonToObject(CameraFromJsonDto[].class, camerasJson);
        for (CameraFromJsonDto cameraFromJsonDto : cameraFromJsonDtos)
        {
            BasicCamera basicCamera;
            if (cameraFromJsonDto.getType() != null && cameraFromJsonDto.getType().equals("DSLR"))
            {
                basicCamera = this.modelParser.mapObject(cameraFromJsonDto, DSLRCamera.class);
                if (validateCamera(basicCamera))
                {
                    this.cameraRepository.save(basicCamera);
                    System.out.println("Successfully imported DSLR " + basicCamera.getMake() + " " + basicCamera.getModel());
                }
            } else if (cameraFromJsonDto.getType() != null && cameraFromJsonDto.getType().equals("Mirrorless"))
            {
                basicCamera = this.modelParser.mapObject(cameraFromJsonDto, MirrorlessCamera.class);
                if (validateCamera(basicCamera))
                {
                    this.cameraRepository.save(basicCamera);
                    System.out.println("Successfully imported Mirrorless " + basicCamera.getMake() + " " + basicCamera.getModel());
                }
            }
        }

    }

    private boolean validateCamera(BasicCamera basicCamera)
    {
        if (this.entitiesValidator.isValid(basicCamera))
        {
            return true;
        }
        System.out.println("Error. Invalid data provided");
        return false;
    }
}
