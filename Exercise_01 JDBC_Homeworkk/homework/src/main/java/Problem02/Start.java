package Problem02;

import java.sql.*;
import java.util.Properties;

public class Start
{
    public static final String URL = "jdbc:mysql://localhost:3306/exercise";

    public static void main(String[] args)
    {
        Properties pros = new Properties();
        pros.setProperty("user", "root");
        pros.setProperty("password", "1234");

        try (Connection connection = DriverManager.getConnection(URL, pros))
        {
            String query = "SELECT\n" +
                    "  v.name,\n" +
                    "  count(mv.villain_id)\n" +
                    "FROM villains v\n" +
                    "  JOIN minions_villains mv ON v.id = mv.villain_id\n" +
                    "GROUP BY mv.villain_id\n" +
                    "HAVING count(mv.villain_id) > 3\n" +
                    "ORDER BY count(mv.villain_id) DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String name = resultSet.getNString(1);
                int villain_count = resultSet.getInt(2);
                System.out.printf("%s %d%n", name, villain_count);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
