package Exercise05;

import enteties.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("SELECT e from Employee as e join Department as d  on e.department.id = d.id and d.name='Research and Development'").getResultList();

        employees.sort((e1, e2) ->
        {
            if (e1.getSalary().compareTo(e2.getSalary()) == 0)
            {
                return e1.getId() - e2.getId();
            }
            return e1.getSalary().compareTo(e2.getSalary());
        });
        employees.forEach(e -> System.out.println(e.getFirstName() + " " + e.getLastName() + " Research and Development - $" + e.getSalary().toString()));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
