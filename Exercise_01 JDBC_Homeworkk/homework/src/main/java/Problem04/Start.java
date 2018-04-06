package Problem04;

import Problem04.Interfaces.MinionDao;
import Problem04.Interfaces.TownDao;
import Problem04.Interfaces.VillainDao;
import Problem04.dao.MinionDaoImpl;
import Problem04.dao.TownDaoImpl;
import Problem04.dao.VillainDaoImpl;
import Problem04.models.Minion;
import Problem04.models.Town;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //read and parse data
        String[] minionInfo = br.readLine().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String townName = minionInfo[3];

        String[] villainInfo = br.readLine().split("\\s+");
        String villianName = villainInfo[1];

        try (MinionDao minionDao = new MinionDaoImpl(); TownDao townDao = new TownDaoImpl(); VillainDao villainDao = new VillainDaoImpl())
        {
            Town town = townDao.checkIfTownExists(townName);
            Minion minion = new Minion(minionName, minionAge, town.getTownId());
            minion = minionDao.checkIfMinionExists(minion);
            villainDao.checkIfVillainExists(villianName, minion);

            minionDao.close();
            townDao.close();
            villainDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}