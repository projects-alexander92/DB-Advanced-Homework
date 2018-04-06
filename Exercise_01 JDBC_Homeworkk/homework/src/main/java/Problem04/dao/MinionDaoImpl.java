package Problem04.dao;

import Problem04.connection.DbConnection;
import Problem04.models.Minion;

import java.sql.*;


public class MinionDaoImpl implements Problem04.Interfaces.MinionDao
{
    private static final String SQL_SELECT_MINION = "SELECT *\n" +
            "FROM minions m JOIN towns t ON m.town_id = t.id\n" +
            "WHERE m.name = ? AND m.age = ? AND m.town_id = ?";
    private static final String SQL_INSERT = "INSERT INTO minions (name, age, town_id) VALUES (?, ?, ?)";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    @Override
    public Minion checkIfMinionExists(Minion minion) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT_MINION);
        this.preparedStatement.setString(1, minion.getName());
        this.preparedStatement.setInt(2, minion.getAge());
        this.preparedStatement.setInt(3, minion.getTownId());
        this.resultSet = this.preparedStatement.executeQuery();

        if (!resultSet.first())
        {
            return this.addMinionToDb(minion);
        } else
        {
            //get the minion id
            resultSet.first();
            minion.setId(resultSet.getInt("id"));
            return minion;
        }
    }

    private Minion addMinionToDb(Minion minion) throws SQLException
    {
        //insert the minion
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_INSERT);
        this.preparedStatement.setString(1, minion.getName());
        this.preparedStatement.setInt(2, minion.getAge());
        this.preparedStatement.setInt(3, minion.getTownId());
        this.preparedStatement.executeUpdate();

        //get the minion id
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT_MINION);
        this.preparedStatement.setString(1, minion.getName());
        this.preparedStatement.setInt(2, minion.getAge());
        this.preparedStatement.setInt(3, minion.getTownId());
        this.resultSet = this.preparedStatement.executeQuery();
        resultSet.first();
        minion.setId(resultSet.getInt("id"));
        return minion;
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
