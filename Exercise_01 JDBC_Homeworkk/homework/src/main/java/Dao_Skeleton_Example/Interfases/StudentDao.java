package Dao_Skeleton_Example.Interfases;

import Dao_Skeleton_Example.models.Student;

import java.sql.SQLException;
import java.util.List;

//when we are do with try it will auto close the statements
public interface StudentDao extends AutoCloseable
{
    List<Student> getAllStudents() throws SQLException;

    void insertStudent(Student student) throws SQLException;

    void updateStudent(Student student) throws SQLException;

    void deleteStudent(Student student) throws SQLException;
}
