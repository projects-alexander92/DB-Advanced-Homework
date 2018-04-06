package Problem09;

import Problem09.dao.MinionsDaoImpl;
import Problem09.interfaces.MinionsDao;
import Problem09.models.Minion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(br.readLine());

        try (MinionsDao minionsDao = new MinionsDaoImpl())
        {
            minionsDao.updateMinionAgeById(id);
            Minion minion = minionsDao.selectMinionById(id);
            System.out.println(minion);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
