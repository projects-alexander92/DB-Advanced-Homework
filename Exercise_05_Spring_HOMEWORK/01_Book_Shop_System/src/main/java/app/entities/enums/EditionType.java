package app.entities.enums;

public enum EditionType
{
    NORMAL(1), PROMO(0), GOLD(2);

    private final int value;

    EditionType(int i)
    {
        this.value = i;
    }

    public int getValue()
    {
        return this.value;
    }
}
