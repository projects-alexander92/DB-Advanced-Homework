package Problem05;

import Problem05.dao.TownDaoImpl;
import Problem05.interfacses.TownDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        try (TownDao townDao = new TownDaoImpl())
        {
            townDao.updateTowns(s);
            townDao.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
