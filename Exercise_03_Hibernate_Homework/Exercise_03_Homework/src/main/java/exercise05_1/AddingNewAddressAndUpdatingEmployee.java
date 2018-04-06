package exercise05_1;

import enteties.Address;
import enteties.Employee;
import enteties.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AddingNewAddressAndUpdatingEmployee
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lastName = br.readLine();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        List<Town> towns = entityManager.createQuery("SELECT t FROM Town t where t.name='Sofia'").getResultList();
        address.setTown(towns.get(0));
        List<Employee> employees = entityManager.createQuery("SELECT e from Employee  as  e where e.lastName = ?").setParameter(0, lastName).getResultList();
        employees.get(0).setAddress(address);
        entityManager.persist(address);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
