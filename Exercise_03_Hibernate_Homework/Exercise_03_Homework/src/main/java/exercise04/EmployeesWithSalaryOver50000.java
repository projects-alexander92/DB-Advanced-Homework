package exercise04;

import enteties.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("SELECT e from Employee as e where e.salary > 50000").getResultList();
        employees.forEach(e -> System.out.println(e.getFirstName()));


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
