package app.services.interfaces;

import app.entities.Categorie;

import java.util.List;

public interface CategorieService
{
    Categorie findById(long id);

    void saveCategorie(Categorie categorie);

    List<Categorie> getAll();
}
