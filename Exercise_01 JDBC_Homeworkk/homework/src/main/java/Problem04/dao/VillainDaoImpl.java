package Problem04.dao;

import Problem04.connection.DbConnection;
import Problem04.models.Minion;
import Problem04.models.Villain;

import java.sql.*;

public class VillainDaoImpl implements Problem04.Interfaces.VillainDao
{
    private static final String SQL_SELECT = "SELECT * FROM villains v WHERE v.name =?";
    private static final String SQL_INSERT = "INSERT INTO villains(name, evilness_factor) VALUES (?, ?)";
    private static final String SQL_MAP = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?)";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void checkIfVillainExists(String villainName, Minion minion) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT);
        this.preparedStatement.setString(1, villainName);
        this.resultSet = this.preparedStatement.executeQuery();
        //if not exist create one and return
        if (!resultSet.first())
        {
            Villain villain = this.insertVillain(villainName);
            //populate mapping table
            populateMappingTable(villain, minion);

        } else
        {
            //else parse and return
            Villain villain = new Villain();
            resultSet.first();
            villain.setName(villainName);
            villain.setId(resultSet.getInt("id"));

            //populate mapping table
            populateMappingTable(villain, minion);
        }
    }

    private Villain insertVillain(String villainName) throws SQLException
    {
        //insert in to db
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_INSERT);
        this.preparedStatement.setString(1, villainName);
        this.preparedStatement.setString(2, "evil");
        if (this.preparedStatement.executeUpdate() == 1)
        {
            System.out.println("Villain " + villainName + " was added to the database.");
        }

        //get id;
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_SELECT);
        this.preparedStatement.setString(1, villainName);
        this.resultSet = this.preparedStatement.executeQuery();
        this.resultSet.first();
        Villain villain = new Villain();
        villain.setName(villainName);
        villain.setId(this.resultSet.getInt("id"));
        return villain;
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

    //TODO
    private void populateMappingTable(Villain villain, Minion minion) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_MAP);
        this.preparedStatement.setInt(1, minion.getId());
        this.preparedStatement.setInt(2, villain.getId());
        if (this.preparedStatement.executeUpdate() == 1)
        {
            System.out.println("Successfully added " + minion.getName() + " to be minion of " + villain.getName());
        }
    }
}
