package Problem08.dao;

import Problem08.connection.DbConnection;
import Problem08.interfaces.MinionDao;

import java.sql.*;

public class MinionsDaoImpl implements MinionDao
{
    private static final String SELECT_ALL_MINIONS = "SELECT m.name, m.age FROM minions m";
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public void updateMinionNamesById(StringBuilder ids) throws SQLException
    {
        String UPDATE_MINION_AGE_BY_ID = "UPDATE minions SET name = CONCAT(UCASE(LEFT(name, 1)),SUBSTRING(name, 2)), age = age + 1 WHERE id IN " + ids.toString();
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(UPDATE_MINION_AGE_BY_ID);
        this.preparedStatement.executeUpdate();
        this.connection.close();
    }

    @Override
    public void selectAllMinions() throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SELECT_ALL_MINIONS);
        this.resultSet = this.preparedStatement.executeQuery();

        while (resultSet.next())
        {
            System.out.printf("%s %d\n", resultSet.getString(1), resultSet.getInt(2));
        }

    }

    @Override
    public void close() throws Exception
    {
        if (this.resultSet != null)
        {
            this.resultSet.close();

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
