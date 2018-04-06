import enteties.User;
import orm.Connector;
import orm.EntityManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Start
{
    public static void main(String[] args) throws SQLException, IOException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Connector.createConnection("root", "1234", "orm_db");
        User user = new User("&^*$%^%$#$", 22, new Date());
        user.setId(16);
        EntityManager<User> em = new EntityManager<>(Connector.getConnection());
        em.findFirst(User.class, "age > 15");
        em.persist(user);
    }
}
