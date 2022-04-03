package ru.geekbrains;

import ru.geekbrains.commands.CommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartManager {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CommandHandler commandHandler = context.getBean(CommandHandler.class);
        commandHandler.handleCommands();
    }
}
