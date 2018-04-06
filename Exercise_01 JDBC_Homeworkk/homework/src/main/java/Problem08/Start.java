package Problem08;

import Problem08.dao.MinionsDaoImpl;
import Problem08.interfaces.MinionDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("\\s+");
        StringBuilder ids = new StringBuilder();
        ids.append("(");
        for (String anArr : arr)
        {
            ids.append(anArr).append(", ");
        }
        ids.delete(ids.lastIndexOf(", "),ids.length());
        ids.append(")");

        try (MinionDao minionDao = new MinionsDaoImpl())
        {
            minionDao.updateMinionNamesById(ids);
            minionDao.selectAllMinions();
            minionDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
