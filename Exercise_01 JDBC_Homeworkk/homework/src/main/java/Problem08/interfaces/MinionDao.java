package Problem08.interfaces;

import java.sql.SQLException;

public interface MinionDao extends AutoCloseable
{
    void updateMinionNamesById(StringBuilder ids) throws SQLException;

    void selectAllMinions() throws SQLException;
}
