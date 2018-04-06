package Problem04.models;

public class Minion
{
    private int id;
    private String name;
    private int age;
    private int townId;

    public Minion()
    {

    }

    public Minion(String name, int age, int townId)
    {
        this.name = name;
        this.age = age;
        this.townId = townId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getTownId()
    {
        return townId;
    }

    public void setTownId(int townId)
    {
        this.townId = townId;
    }
}
