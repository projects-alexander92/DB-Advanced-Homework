package exercise08;

import enteties.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class FindLatest10Projects
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery("select p from Project p order by p.id desc").setMaxResults(10).getResultList();
        projects.stream().sorted(Comparator.comparing(Project::getName))
                .forEach(e ->
                {
                    System.out.println("Project name: " + e.getName());
                    System.out.println("Project Description: " + e.getDescription());
                    System.out.println("Project Start Date: " + e.getStartDate());
                    System.out.println("Project End Date: " + e.getEndDate());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
