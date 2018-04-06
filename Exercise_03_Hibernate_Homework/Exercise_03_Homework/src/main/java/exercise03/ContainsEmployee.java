package exercise03;

import enteties.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ContainsEmployee
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\s+");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("SELECT e from Employee as e where e.firstName = ? and e.lastName = ?")
                .setParameter(0, input[0])
                .setParameter(1, input[1])
                .getResultList();
        if (employees.size() > 0)
        {
            System.out.println("Yes");
        } else
        {
            System.out.println("No");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
