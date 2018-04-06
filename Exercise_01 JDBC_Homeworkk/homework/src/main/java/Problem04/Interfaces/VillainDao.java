package Problem04.Interfaces;

import Problem04.models.Minion;
import Problem04.models.Villain;

import java.sql.SQLException;

public interface VillainDao extends AutoCloseable
{
    void checkIfVillainExists(String villainName, Minion minion) throws SQLException;
}
