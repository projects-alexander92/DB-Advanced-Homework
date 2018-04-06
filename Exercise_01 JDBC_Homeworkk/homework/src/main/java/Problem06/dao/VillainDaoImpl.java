package Problem06.dao;

import Problem05.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VillainDaoImpl implements Problem06.interfaces.VillainDao
{
    private static final String SQL_GET_VILLAIN_BY_ID = "SELECT v.name FROM villains v WHERE id = ?";
    private static final String SQL_REMOVE_FROM_MAPPING_TABLE = "DELETE FROM minions_villains WHERE villain_id = ?";
    private static final String SQL_DELETE_VILLAIN_BY_ID = "DELETE FROM villains WHERE id = ?";
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public String getVillainById(int id) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_GET_VILLAIN_BY_ID);
        this.preparedStatement.setInt(1, id);
        this.resultSet = this.preparedStatement.executeQuery();

        if (!this.resultSet.first())
        {
            return null;
        } else
        {
            this.resultSet.first();
            String villainName = this.resultSet.getString("name");
            return villainName;
        }
    }

    @Override
    public void deleteVillain(int id, String name) throws SQLException
    {

        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_REMOVE_FROM_MAPPING_TABLE);
        this.preparedStatement.setInt(1, id);
        int minionsReleased = preparedStatement.executeUpdate();

        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_DELETE_VILLAIN_BY_ID);
        this.preparedStatement.setInt(1, id);
        int i = this.preparedStatement.executeUpdate();

        if (i > 0)
        {
            System.out.println(name + " was deleted");
            System.out.println(minionsReleased + " minions released");
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
