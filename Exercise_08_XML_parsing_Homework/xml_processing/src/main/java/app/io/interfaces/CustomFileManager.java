package app.io.interfaces;

import java.io.IOException;

public interface CustomFileManager
{
    String convertFileToString(String path) throws IOException;

    void writeStringToFile(String path, String content) throws IOException;
}
