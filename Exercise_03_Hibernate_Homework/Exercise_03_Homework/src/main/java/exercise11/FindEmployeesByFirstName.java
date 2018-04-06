package exercise11;

import enteties.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindEmployeesByFirstName
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        name = name + "%";
        System.out.println(name);
        List<Employee> employees = entityManager.createQuery("select e from Employee e where e.firstName like ?").setParameter(0, name).getResultList();
        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)\n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
