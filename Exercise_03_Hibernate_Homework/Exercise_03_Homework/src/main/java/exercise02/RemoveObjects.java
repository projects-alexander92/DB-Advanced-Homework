package exercise02;

import enteties.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RemoveObjects
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Town> towns = entityManager.createQuery("SELECT t from Town as t where length(t.name) > 5 ").getResultList();
        towns.forEach(e->{
            entityManager.detach(e);
            e.setName(e.getName().toLowerCase());
            entityManager.merge(e);
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
