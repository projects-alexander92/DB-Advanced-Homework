package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import enteties.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EntityManager<E extends User> implements DbContext<E>
{
    private Connection connection;

    public EntityManager(Connection connection)
    {
        this.connection = connection;
    }

    //Persistvame obecti v tablicata
    //gledame dali id-to mu e zadadeno ili e <1
    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException
    {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object fieldValue = primary.get(entity);
        //checks if user exists or not to decide to update or insert
        if (fieldValue == null || (Integer) fieldValue <= 1)
        {
            return this.doInsert(entity, primary);
        }
        return this.doUpdate(entity, primary);
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException
    {
        StringBuilder fieldNamesAndValues = new StringBuilder();
        StringBuilder where = new StringBuilder();
        Field[] declaredFields = entity.getClass().getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++)
        {
            Field currentField = declaredFields[i];
            currentField.setAccessible(true);

            if (currentField.getName().equals(primary.getName()))
            {
                where.append(" WHERE ")
                        .append("`")
                        .append(currentField.getAnnotation(Column.class).name())
                        .append("` = '")
                        .append(currentField.get(entity))
                        .append("'");
                continue;
            } else
            {
                if (currentField.get(entity) instanceof Date)
                {
                    fieldNamesAndValues
                            .append("`")
                            .append(currentField.getAnnotation(Column.class).name())
                            .append("` = '")
                            .append(new SimpleDateFormat("yyyy-MM-dd").format(currentField.get(entity)))
                            .append("'");
                    if (i < declaredFields.length - 1)
                    {
                        fieldNamesAndValues.append(", ");
                    }
                } else
                {
                    fieldNamesAndValues
                            .append("`")
                            .append(currentField.getAnnotation(Column.class).name())
                            .append("` = '")
                            .append(currentField.get(entity))
                            .append("'");
                }
            }
            if (i < declaredFields.length - 1)
            {
                fieldNamesAndValues.append(", ");
            }
        }
        String query = "UPDATE " + this.getTableName(entity.getClass()) + " SET " + fieldNamesAndValues.toString() + where.toString();
        System.out.println(query);
        return this.connection.prepareStatement(query).execute();
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException
    {
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        //parse the query with reflection
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length - 1; i++)
        {
            Field currentField = declaredFields[i];
            if (!currentField.isAnnotationPresent(Id.class) && currentField.isAnnotationPresent(Column.class))
            {
                currentField.setAccessible(true);
                if (currentField.get(entity) instanceof Date)
                {
                    fields.append(currentField.getAnnotation(Column.class).name()).append(", ");
                    values.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format(currentField.get(entity))).append("'").append(", ");
                } else
                {
                    fields.append(currentField.getAnnotation(Column.class).name()).append(", ");
                    values.append("'").append(currentField.get(entity)).append("'").append(", ");
                }
            }
        }
        //append last
        Field field = declaredFields[declaredFields.length - 1];
        field.setAccessible(true);
        if (field.get(entity) instanceof Date)
        {
            values.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format(field.get(entity))).append("'");
        } else
        {
            values.append("'").append(declaredFields[declaredFields.length - 1].get(entity)).append("'");
        }
        fields.append(declaredFields[declaredFields.length - 1].getAnnotation(Column.class).name());
        String query = "INSERT INTO " + this.getTableName(entity.getClass()) + " (" + fields + ") VALUES (" + values + ")";
        System.out.println(query);
        return this.connection.prepareStatement(query).execute();

    }

    private String getTableName(Class entity)
    {
        String tableName = "";
        if (entity.isAnnotationPresent(Entity.class))
        {
            Entity annotation = (Entity) entity.getAnnotation(Entity.class);
            tableName = annotation.name();
        }
        if (tableName.equals(""))
        {
            tableName = entity.getSimpleName();
        }
        return tableName;
    }

    @Override
    public Iterable<E> find(Class<E> table) throws SQLException
    {
        return this.find(table, null);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws SQLException
    {
        Statement stmt = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " WHERE 1 " + (where != null ? "AND " + where : "");
        ResultSet rs = stmt.executeQuery(query);

        List<E> result = new ArrayList<>();
        while (rs.next())
        {
            User user = new User(rs.getString("username"), Integer.parseInt(rs.getString("age")),
                    rs.getDate("registration_date"));
            user.setId(Integer.parseInt(rs.getString("id")));
            result.add((E) user);
        }
        return result;
    }

    @Override
    public E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException
    {

        return this.findFirst(table, null);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        Statement stmt = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " WHERE 1 " + (where != null ? " AND " + where : "") + " LIMIT 1";
        System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        E entity = table.getConstructor().newInstance();
        rs.next();
        this.fillEntity(table, entity, rs);
        return entity;

    }

    private void fillEntity(Class table, Object entity, ResultSet rs) throws SQLException,
            IllegalAccessException
    {
        for (int i = 0; i < table.getDeclaredFields().length; i++)
        {
            Field field = table.getDeclaredFields()[i];
            field.setAccessible(true);
            fillField(field, entity, rs, field.getAnnotation(Column.class).name());
        }
    }

    private static void fillField(Field field, Object instance, ResultSet rs, String fieldName)
            throws SQLException, IllegalAccessException
    {
        field.setAccessible(true);
        if (field.getType() == int.class || field.getType() == Integer.class)
        {
            field.set(instance, rs.getInt(fieldName));
        } else if (field.getType() == long.class || field.getType() == Long.class)
        {
            field.set(instance, rs.getLong(fieldName));
        } else if (field.getType() == double.class || field.getType() == Double.class)
        {
            field.set(instance, rs.getDouble(fieldName));
        } else if (field.getType() == String.class)
        {
            field.set(instance, rs.getString(fieldName));
        } else if (field.getType() == Date.class)
        {
            field.set(instance, rs.getDate(fieldName));
        }
    }

    //find primary key
    private Field getId(Class entity)
    {
        return Arrays.stream(entity
                .getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Id annotation is not present"));
    }
}
