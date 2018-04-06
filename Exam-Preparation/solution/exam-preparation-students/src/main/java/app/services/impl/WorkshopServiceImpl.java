package app.services.impl;

import app.config.FilePaths;
import app.enitties.orm.Photographer;
import app.enitties.orm.Workshop;
import app.enitties.validation.EntitiesValidator;
import app.enitties.xmlDto.workshopFromXml.PhotographerFromXml;
import app.enitties.xmlDto.workshopFromXml.WorkshopFromXml;
import app.enitties.xmlDto.workshopFromXml.WorkshopsFromXml;
import app.parsers.interfaces.ModelParser;
import app.parsers.interfaces.XmlParser;
import app.repositories.PhotographerRepository;
import app.repositories.WorkShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;

@Service
public class WorkshopServiceImpl implements app.services.interfaces.WorkshopService
{
    @Autowired
    private WorkShopRepository workShopRepository;
    @Autowired
    private ModelParser modelParser;
    @Autowired
    private XmlParser xmlParser;
    @Autowired
    private EntitiesValidator entitiesValidator;
    @Autowired
    private PhotographerRepository photographerRepository;


    @Override
    @Transactional
    public void importWorkshopsFromXmlFile() throws JAXBException
    {
        WorkshopsFromXml workshopsFromXml = this.xmlParser.xmlToObject(WorkshopsFromXml.class, FilePaths.WORKSHOPS_XML);
        for (WorkshopFromXml workshopFromXml : workshopsFromXml.getWorkshops())
        {

            Workshop workshop = this.modelParser.mapObject(workshopFromXml, Workshop.class);
            //set the trainer
            String[] fullName = new String[2];
            if (workshopFromXml.getTrainer() != null)
            {
                fullName = workshopFromXml.getTrainer().split(" ");
            }
            Photographer photographer = this.photographerRepository.findByFirstNameAndLastName(fullName[0], fullName[1]);
            workshop.setTrainer(photographer);
            //set the trainees
            for (PhotographerFromXml photographerFromXml : workshopFromXml.getPhotographers())
            {
                String firstName = photographerFromXml.getFirstName();
                String lastName = photographerFromXml.getLastName();
                Photographer photographer1 = this.photographerRepository.findByFirstNameAndLastName(firstName, lastName);

                if (photographer1 != null)
                {
                    workshop.getParticipants().add(photographer1);
                }
            }
            //validate the worksho
            if (validateWorkshop(workshop))
            {
                this.workShopRepository.save(workshop);
            }

        }
    }

    private boolean validateWorkshop(Workshop workshop)
    {
        if (this.entitiesValidator.isValid(workshop))
        {
            return true;
        }
        System.out.println("Error. Invalid data provided");
        return false;
    }
}
