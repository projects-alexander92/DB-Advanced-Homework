package Problem07;

import Problem07.Interfaces.MinionDao;
import Problem07.dao.MinionDaoImpl;

import java.util.List;


public class Start
{
    public static void main(String[] args)
    {
        try (MinionDao minionDao = new MinionDaoImpl())
        {
            List<String> allMinionNames = minionDao.getAllMinionNames();
            String[] minionNames = new String[allMinionNames.size()];
            minionNames = allMinionNames.toArray(minionNames);
            int index = 1;
            for (int i = 0; i < minionNames.length / 2; i++)
            {
                System.out.print(minionNames[i] + " ");
                System.out.print(minionNames[minionNames.length - index] + " ");

                index++;
                if (minionNames.length % 2 != 0 && index > minionNames.length / 2)
                {
                    System.out.println(minionNames[index - 1]);
                }
            }
            minionDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}