package Problem04.models;

public class Villain
{
    private String name;
    private String evilnessFactor;
    private int id;

    public Villain()
    {

    }

    public Villain(String name)
    {
        this.name = name;
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

    public String getEvilnessFactor()
    {
        return evilnessFactor;
    }

    public void setEvilnessFactor(String evilnessFactor)
    {
        this.evilnessFactor = evilnessFactor;
    }
}
