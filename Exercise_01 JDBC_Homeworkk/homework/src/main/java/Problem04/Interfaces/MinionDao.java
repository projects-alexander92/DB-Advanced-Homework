package Problem04.Interfaces;

import Problem04.models.Minion;

import java.sql.SQLException;

public interface MinionDao extends AutoCloseable
{
    Minion checkIfMinionExists(Minion minion) throws SQLException;
}
