package exercise12;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries
{
    public static void main(String[] args)
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Object[]> objects = entityManager.createQuery("select max(e.salary), e.department.name from Employee e group by e.department.id HAVING max(e.salary) < 30000  OR max(e.salary) > 70000").getResultList();
        objects.forEach(e -> System.out.printf("%s - %.2f\n", e[1], e[0]));
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
