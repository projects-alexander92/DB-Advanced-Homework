package Problem09.interfaces;

import Problem09.models.Minion;

import java.sql.SQLException;

public interface MinionsDao extends AutoCloseable
{
    void updateMinionAgeById(int id) throws SQLException;

    Minion selectMinionById(int id) throws SQLException;

    void close() throws Exception;
}
