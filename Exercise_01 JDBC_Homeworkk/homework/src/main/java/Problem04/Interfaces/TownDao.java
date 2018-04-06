package Problem04.Interfaces;

import Problem04.models.Town;

import java.sql.SQLException;

public interface TownDao extends AutoCloseable
{
    Town checkIfTownExists(String townName) throws SQLException;
}
