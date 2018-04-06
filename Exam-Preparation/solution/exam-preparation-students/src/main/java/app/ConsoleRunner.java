package app;

import app.services.interfaces.AccessoryService;
import app.services.interfaces.CameraService;
import app.services.interfaces.PhotographerService;
import app.services.interfaces.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner
{
    private final CameraService cameraService;
    private final PhotographerService photographerService;
    private final AccessoryService accessoryService;
    private final WorkshopService workshopService;

    @Autowired
    public ConsoleRunner(CameraService cameraService, PhotographerService photographerService, AccessoryService accessoryService, WorkshopService workshopService)
    {
        this.cameraService = cameraService;
        this.photographerService = photographerService;
        this.accessoryService = accessoryService;
        this.workshopService = workshopService;
    }

    @Override
    public void run(String... strings) throws Exception
    {
        parseDateFromJsonFiles();
    }

    private void parseDateFromJsonFiles() throws IOException, JAXBException
    {
//        Task 01
        this.cameraService.importCamerasFromJsonFile();
//        Task 02
        this.photographerService.importPhotographersFromJsonFile();
////        Task 03
        this.accessoryService.importAccessoriesFromXmlFile();
////        Task 04
        this.workshopService.importWorkshopsFromXmlFile();
////        Task 05
//        this.photographerService.exportPhotographersToJson();
////        Task 06
//        this.photographerService.photographerAndCamerasToXml();

    }
}
