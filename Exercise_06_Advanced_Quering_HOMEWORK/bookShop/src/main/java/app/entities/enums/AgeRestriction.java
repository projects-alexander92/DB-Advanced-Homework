package app.entities.enums;

public enum AgeRestriction
{
    MINOR(0), TEEN(1), ADULT(2);
    private final int value;

    AgeRestriction(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
