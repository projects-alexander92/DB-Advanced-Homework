package Problem03;

import Problem03.dao.interfaces.MinionDao;
import Problem03.dao.MinionDaoImpl;
import Problem03.dao.interfaces.VillainDao;
import Problem03.dao.VillainDaoImpl;
import Problem03.models.Minion;
import Problem03.models.Villain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class Start
{
    private static final String URL = "jdbc:mysql://localhost:3306/exercise";

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Properties pros = new Properties();
        pros.setProperty("user", "root");
        pros.setProperty("password", "1234");

        try (VillainDao villainDao = new VillainDaoImpl(); MinionDao minionDao = new MinionDaoImpl())
        {
            String select_villain = "SELECT v.name\n" +
                    "FROM villains v\n" +
                    "WHERE v.id = ?";

            //get minions
            int villain_id = Integer.parseInt(br.readLine());
            Villain villain = villainDao.selectVillainById(villain_id);
            if (villain == null)
            {
                System.out.printf("No villain with ID %d exists in the database.", villain_id);
                return;
            } else
            {
                System.out.printf("Villain: %s%n", villain.getName());
            }
            List<Minion> allMinionsByVillainId = minionDao.getAllMinionsByVillainId(villain_id);

            //check if no minions
            if (allMinionsByVillainId.size() < 1)
            {
                System.out.print("<no minions>");
                return;
            }
            int counter = 0;
            //print minions
            for (Minion minion : allMinionsByVillainId)
            {
                counter++;
                System.out.printf("%d. %s %d%n", counter, minion.getName(), minion.getAge());
            }

            villainDao.close();
            minionDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
