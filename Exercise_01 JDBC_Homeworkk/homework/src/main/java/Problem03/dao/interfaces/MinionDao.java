package Problem03.dao.interfaces;

import Problem03.models.Minion;

import java.sql.SQLException;
import java.util.List;

public interface MinionDao extends AutoCloseable
{
    List<Minion> getAllMinionsByVillainId(int villainId) throws SQLException;
}
