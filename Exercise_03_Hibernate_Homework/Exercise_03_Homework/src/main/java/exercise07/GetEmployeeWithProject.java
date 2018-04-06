package exercise07;

import enteties.Employee;
import enteties.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class GetEmployeeWithProject
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(br.readLine());
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery("select e from Employee as e where e.id =?").setParameter(0, id).getResultList();
        Employee employee = resultList.get(0);

        System.out.printf("%s %s - %s\n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(e-> System.out.println(e.getName()));

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
