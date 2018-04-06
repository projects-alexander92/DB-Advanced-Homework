package app.io;

import app.io.interfaces.CustomFileManager;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CustomFileManagerImpl implements CustomFileManager
{
    public CustomFileManagerImpl()
    {
    }

    @Override
    public String convertFileToString(String path) throws IOException
    {
        InputStream inputStream = getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder file = new StringBuilder();
        String line = br.readLine();
        while (line != null)
        {
            file.append(line);
            line = br.readLine();
        }
        return file.toString();
    }

    @Override
    public void writeStringToFile(String path, String content) throws IOException
    {
        OutputStream outputStream = new FileOutputStream(path);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }
}
