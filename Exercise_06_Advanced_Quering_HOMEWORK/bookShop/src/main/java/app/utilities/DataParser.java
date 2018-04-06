package app.utilities;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;

public interface DataParser
{
    @Transactional
    void parseBooks() throws IOException, ParseException;

    void parseAuthors() throws IOException;

    void parseCategories() throws IOException;
}
