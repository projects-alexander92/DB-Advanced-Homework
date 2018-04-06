package Problem09.dao;

import Problem08.connection.DbConnection;
import Problem08.interfaces.MinionDao;
import Problem09.models.Minion;

import java.sql.*;

public class MinionsDaoImpl implements Problem09.interfaces.MinionsDao
{
    private Connection connection;
    private ResultSet resultSet;
    private CallableStatement callableStatement;
    private static final String SQL_CALL_PROCEDURE = "CALL usp_get_older(?)";
    private static final String SQL_SELECT_BY_ID = "SELECT m.name, m.age FROM minions m WHERE m.id = ?";

    @Override
    public void updateMinionAgeById(int id) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.callableStatement = connection.prepareCall(SQL_CALL_PROCEDURE);
        this.callableStatement.setInt(1, id);
        this.callableStatement.execute();

    }

    @Override
    public Minion selectMinionById(int id) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.callableStatement = connection.prepareCall(SQL_SELECT_BY_ID);
        this.callableStatement.setInt(1, id);
        this.resultSet = this.callableStatement.executeQuery();
        resultSet.first();
        String name = resultSet.getString(1);
        int age = resultSet.getInt(2);
        return new Minion(name, age);
    }

    @Override
    public void close() throws Exception
    {
        if (this.resultSet != null)
        {
            this.resultSet.close();

        }
        if (this.callableStatement != null)
        {
            this.callableStatement.close();
        }
        if (this.connection != null)
        {
            this.connection.close();
        }
    }
}
