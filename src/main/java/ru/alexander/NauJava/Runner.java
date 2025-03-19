package ru.alexander.NauJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Runner {
    @Autowired
    public CommandProcessor commandProcessor;

    @Bean
    public CommandLineRunner commandScanner() {
        return args ->  {
            try (Scanner scanner = new Scanner(System.in)) {
                printInfo();
                while (true) {
                    System.out.print("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы...");
                        break;
                    }
                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    private void printInfo() {
        System.out.println("Введите команду:");
        System.out.println("Выход из программы - 'exit'");
        System.out.println("Создание лога - 'create <id> <level> <yyyy-MM-ddTHH:mm:ss> <source>'");
        System.out.println("Чтение лога - 'read <id>'");
        System.out.println("Обновление лога - 'update <id> <level>'");
        System.out.println("Удаление лога - 'delete <id>'");
        System.out.println("Вывод всех логов - 'all'");
    }
}
