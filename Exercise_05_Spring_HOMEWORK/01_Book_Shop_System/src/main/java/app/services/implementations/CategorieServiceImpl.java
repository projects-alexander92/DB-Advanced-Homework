package app.services.implementations;

import app.entities.Categorie;
import app.repositories.CategorieRepository;
import app.services.interfaces.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService

{
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository)
    {
        this.categorieRepository = categorieRepository;
    }


    @Override
    public Categorie findById(long id)
    {
        return this.categorieRepository.findById(id).get();
    }

    @Override
    public void saveCategorie(Categorie categorie)
    {
        this.categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAll()
    {
        return (List<Categorie>) this.categorieRepository.findAll();
    }
}
