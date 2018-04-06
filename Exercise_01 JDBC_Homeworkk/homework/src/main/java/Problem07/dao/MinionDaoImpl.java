package Problem07.dao;

import Problem07.Interfaces.MinionDao;
import Problem07.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MinionDaoImpl implements MinionDao
{
    private static final String SQL_GET_ALL_MINION_NAMES = "SELECT m.name FROM minions m";
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private List<String> minionNames;

    public MinionDaoImpl()
    {
        this.minionNames = new ArrayList<>();
    }

    @Override
    public List<String> getAllMinionNames() throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_GET_ALL_MINION_NAMES);
        this.resultSet = this.preparedStatement.executeQuery();

        while (this.resultSet.next())
        {
            this.minionNames.add(resultSet.getString(1));
        }
        return this.minionNames;
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
