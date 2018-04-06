package Dao_Skeleton_Example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//if a lot of procceses use 1 connection and there aer a lot of proccses it will put the proccses in queue;
public class DbConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/exercise";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
