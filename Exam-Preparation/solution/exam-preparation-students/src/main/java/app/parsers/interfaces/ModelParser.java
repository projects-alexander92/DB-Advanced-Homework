package app.parsers.interfaces;

public interface ModelParser
{
    <S, D> D mapObject(S source, Class<D> destinationClass);
}
