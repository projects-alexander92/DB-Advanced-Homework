package app.enitties.validation;

public interface EntitiesValidator
{
    <T> boolean isValid(T t);
}
