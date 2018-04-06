package app;

import app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner
{
    private final UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        //Нямахме данни за тази задачка, просто така сум го направил
        getUserByEmailProvider(input);
        deleteByDate(new Date());
    }

    private void getUserByEmailProvider(String emailProvider)
    {
        this.userService.findByEmail(emailProvider);
    }

    private void deleteByDate(Date date)
    {
        this.userService.deleteByDateBefore(date);
    }
}
