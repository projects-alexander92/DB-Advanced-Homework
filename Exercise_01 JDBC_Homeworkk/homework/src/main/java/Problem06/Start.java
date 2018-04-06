package Problem06;

import Problem06.dao.VillainDaoImpl;
import Problem06.interfaces.VillainDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int villainId = Integer.parseInt(br.readLine());
        try (VillainDao villainDao = new VillainDaoImpl())
        {
            String villainName = villainDao.getVillainById(villainId);
            if (villainName == null)
            {
                System.out.println("No such villain was found");
            } else
            {
                villainDao.deleteVillain(villainId, villainName);
            }
            villainDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
