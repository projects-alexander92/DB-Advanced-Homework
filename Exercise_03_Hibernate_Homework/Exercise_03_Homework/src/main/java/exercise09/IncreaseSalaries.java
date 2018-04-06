package exercise09;

import enteties.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createQuery("select e from Employee e where e.department.name='Engineering' or e.department.name='Tool Design' or e.department.name ='Marketing' or e.department.name='Information Services'").getResultList();
        System.out.println(employees);

        employees.forEach(e ->
        {
            BigDecimal salary = e.getSalary();
            e.setSalary(salary.add(salary.multiply(new BigDecimal(0.12))));
            System.out.printf("%s %s (%.2f)\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
