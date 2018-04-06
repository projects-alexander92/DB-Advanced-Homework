package Problem03.dao;

import Problem03.connection.DbConnection;
import Problem03.dao.interfaces.VillainDao;
import Problem03.models.Villain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VillainDaoImpl implements VillainDao
{
    private static final String SELECT_VILLAIN = "SELECT v.name\n" +
            "FROM villains v\n" +
            "WHERE v.id = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Villain selectVillainById(int villainId) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SELECT_VILLAIN);
        this.preparedStatement.setInt(1, villainId);
        this.resultSet = this.preparedStatement.executeQuery();
        if (!resultSet.first())
        {
            return null;
        }
        this.resultSet.first();
        Villain villain = new Villain(this.resultSet.getString(1));
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
}
