package Problem04.dao;

import Problem04.connection.DbConnection;
import Problem04.models.Town;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TownDaoImpl implements Problem04.Interfaces.TownDao
{
    private static final String SQL_SELECT = "SELECT * FROM towns t WHERE t.name=?";
    private static final String SQL_INSERT = "INSERT INTO towns (name, coutry) VALUES (?, NULL)";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Town checkIfTownExists(String townName) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT);
        this.preparedStatement.setString(1, townName);
        this.resultSet = this.preparedStatement.executeQuery();
        //if not exist create one and return
        if (!resultSet.first())
        {
            return this.insertTown(townName);
        } else
        {
            //else parse and return
            Town town = new Town();
            resultSet.first();
            town.setTownName(resultSet.getString("name"));
            town.setTownId(resultSet.getInt("id"));
            return town;
        }
    }

    private Town insertTown(String townName) throws SQLException
    {
        //insert the Town
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_INSERT);
        this.preparedStatement.setString(1, townName);
        if (this.preparedStatement.executeUpdate() == 1)
        {
            System.out.println("Town " + townName + " was added to the database.");
        }
        //get the Town id
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT);
        this.preparedStatement.setString(1, townName);
        this.resultSet = this.preparedStatement.executeQuery();
        //parse and return
        Town town = new Town();
        resultSet.next();
        town.setTownName(resultSet.getString("name"));
        town.setTownId(resultSet.getInt("id"));
        return town;
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
