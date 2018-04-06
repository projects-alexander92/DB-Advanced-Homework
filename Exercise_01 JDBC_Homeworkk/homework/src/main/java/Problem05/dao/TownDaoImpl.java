package Problem05.dao;

import Problem05.connection.DbConnection;
import Problem05.interfacses.TownDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TownDaoImpl implements TownDao
{
    private static final String SQL_UPDATE_BY_COUNTRY_NAME = "UPDATE towns t SET t.name = upper(t.name) WHERE t.coutry = ?";
    private static final String SQL_SELECT_BY_COUNTRY_NAME = "SELECT t.name FROM towns t WHERE t.coutry = ?";
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    @Override
    public void updateTowns(String countryName) throws SQLException
    {
        this.connection = DbConnection.getConnection();
        this.preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_COUNTRY_NAME);
        this.preparedStatement.setString(1, countryName);
        int i = this.preparedStatement.executeUpdate();

        if (i == 0)
        {
            System.out.println("No town names were affected.");

        } else
        {
            System.out.printf("%d town names were affected. %n", i);

            this.connection = DbConnection.getConnection();
            this.preparedStatement = connection.prepareStatement(SQL_SELECT_BY_COUNTRY_NAME);
            this.preparedStatement.setString(1, countryName);
            this.resultSet = this.preparedStatement.executeQuery();

            List<String> names = new ArrayList<>();
            while (resultSet.next())
            {
                names.add(resultSet.getString("name"));
            }
            System.out.println(names);
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
