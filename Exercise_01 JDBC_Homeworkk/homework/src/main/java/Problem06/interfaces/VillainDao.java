package Problem06.interfaces;

import java.sql.SQLException;

public interface VillainDao extends AutoCloseable
{
    String getVillainById(int id) throws SQLException;

    void deleteVillain(int id, String name) throws SQLException;
}
