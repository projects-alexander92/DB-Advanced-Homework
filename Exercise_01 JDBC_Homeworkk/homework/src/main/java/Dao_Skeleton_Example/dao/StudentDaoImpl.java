package Dao_Skeleton_Example.dao;

import Dao_Skeleton_Example.Interfases.StudentDao;
import Dao_Skeleton_Example.connection.DbConnection;
import Dao_Skeleton_Example.models.Student;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class StudentDaoImpl implements StudentDao
{
    private static final String sqlSelect = "";
    private static final String sqlInsert = "";
    private static final String sqlUpdate = "";
    private static final String sqlDelete = "";
    private List<Student> students;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultset;

    public StudentDaoImpl()
    {
        this.students = new ArrayList<>();
    }

    @Override
    public List<Student> getAllStudents() throws SQLException
    {
        connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(sqlSelect);
        this.resultset = this.preparedStatement.executeQuery();
        //some logic
        return null;
    }

    @Override
    public void insertStudent(Student student) throws SQLException
    {
        connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(sqlSelect);
    }

    @Override
    public void updateStudent(Student student) throws SQLException
    {
        connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(sqlSelect);
    }

    @Override
    public void deleteStudent(Student student) throws SQLException
    {
        connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(sqlSelect);
    }

    @Override
    public void close() throws Exception
    {
        if (this.resultset != null)
        {
            this.resultset.close();
        }
        if (this.preparedStatement != null)
        {
            this.preparedStatement.close();
        }
        if (this.connection != null)
        {
            this.connection.close();
        }
    }
}
