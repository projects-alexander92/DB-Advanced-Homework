package app.parsers.interfaces;

import java.io.IOException;

public interface JsonParser
{
    <T> T fromJsonToObject(Class<T> objectClass, String line) throws IOException;

    <T> String fromObjectToJson(T object) throws IOException;
}
