package app.parsers.impl;

import app.parsers.interfaces.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonParserImpl implements JsonParser
{
    private Gson gson;

    public JsonParserImpl()
    {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Override
    public <T> T fromJsonToObject(Class<T> objectClass, String line) throws IOException
    {
        T object = this.gson.fromJson(line, objectClass);
        return object;
    }

    @Override
    public <T> String fromObjectToJson(T object) throws IOException
    {
        String jsonContent = this.gson.toJson(object);
        return jsonContent;

    }
}
