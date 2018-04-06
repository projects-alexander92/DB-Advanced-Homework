package Problem04.models;

public class Town
{
    private String townName;
    private String countryName;
    private int townId;

    public Town()
    {

    }

    public Town(String townName, String countryName)
    {
        this.townName = townName;
        this.countryName = countryName;
    }

    public int getTownId()
    {
        return townId;
    }

    public void setTownId(int townId)
    {
        this.townId = townId;
    }

    public String getTownName()
    {
        return townName;
    }

    public void setTownName(String townName)
    {
        this.townName = townName;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String countryName)
    {
        this.countryName = countryName;
    }
}
