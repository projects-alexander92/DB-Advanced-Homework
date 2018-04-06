package Problem03.dao;


import Problem03.connection.DbConnection;
import Problem03.dao.interfaces.MinionDao;
import Problem03.models.Minion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MinionDaoImpl implements MinionDao
{
    private static final String SELECT_MINIONS_BY_VILLAIN_ID = "SELECT\n" +
            "  m.name,\n" +
            "  m.age\n" +
            "FROM minions m\n" +
            "  JOIN minions_villains mv ON m.id = mv.minion_id\n" +
            "WHERE mv.villain_id = ?";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Minion> getAllMinionsByVillainId(int villainId) throws SQLException
    {
        List<Minion> minions = new ArrayList<>();
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SELECT_MINIONS_BY_VILLAIN_ID);
        this.preparedStatement.setInt(1, villainId);
        this.resultSet = preparedStatement.executeQuery();

        //parseMinion
        while (resultSet.next())
        {
            String minionName = resultSet.getString(1);
            int minionAge = resultSet.getInt(2);
            minions.add(new Minion(minionName, minionAge));
        }
        return minions;
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
