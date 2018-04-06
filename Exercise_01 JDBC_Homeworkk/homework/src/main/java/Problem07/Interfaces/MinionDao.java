package Problem07.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface MinionDao extends AutoCloseable
{
    List<String> getAllMinionNames() throws SQLException;
}
