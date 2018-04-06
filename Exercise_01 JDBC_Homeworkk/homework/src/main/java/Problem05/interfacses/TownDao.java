package Problem05.interfacses;

import java.sql.SQLException;

public interface TownDao extends AutoCloseable
{
    void updateTowns(String countryName) throws SQLException;
}
