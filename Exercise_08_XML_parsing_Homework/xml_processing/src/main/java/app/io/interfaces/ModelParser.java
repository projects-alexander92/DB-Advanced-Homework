package app.io.interfaces;

public interface ModelParser
{
    <S, D> D parse(S source, Class<D> destinationClass);
}
