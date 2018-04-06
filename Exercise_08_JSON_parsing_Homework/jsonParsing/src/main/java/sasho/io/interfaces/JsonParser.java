package sasho.io.interfaces;

import java.io.IOException;

public interface JsonParser
{
    <T> T fromJsonToObject(Class<T> objectClass, String line) throws IOException;

    <T> String write(T object) throws IOException;
}
