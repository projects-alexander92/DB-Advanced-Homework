package Dao_Skeleton_Example;

import Dao_Skeleton_Example.Interfases.StudentDao;
import Dao_Skeleton_Example.dao.StudentDaoImpl;
import Dao_Skeleton_Example.models.Student;


public class Start
{
    public static final String URL = "jdbc:mysql://localhost:3306/exercise";

    public static void main(String[] args)
    {
        try (StudentDao studentDao = new StudentDaoImpl())
        {
            Student a = new Student();
            a.setId(1);
            a.setName("daasd");
            studentDao.insertStudent(a);
            studentDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
