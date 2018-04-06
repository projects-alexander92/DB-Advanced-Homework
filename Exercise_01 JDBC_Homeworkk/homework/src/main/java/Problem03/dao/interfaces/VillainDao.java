package Problem03.dao.interfaces;

import Problem03.models.Villain;

import java.sql.SQLException;

public interface VillainDao extends AutoCloseable
{
    Villain selectVillainById(int villainId) throws SQLException;
}
