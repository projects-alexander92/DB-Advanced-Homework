package exercise06;

import enteties.Address;
import enteties.Employee;
import enteties.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Address> resultList =
                entityManager.createQuery("select a from Address as a " +
                        "order by a.employees.size desc")
                .setMaxResults(10)
                .getResultList();
        for (Address current : resultList)
        {
            System.out.printf("%d, %s, %s - %d employees\n", current.getId(), current.getText(), current.getTown().getName(), current.getEmployees().size());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
