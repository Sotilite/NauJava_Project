package ru.alexander.NauJava;

import Core.LogEvent;
import Service.LogEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommandProcessor {
    private final LogEventServiceImpl logEventService;

    @Autowired
    public CommandProcessor(LogEventServiceImpl logEventService) {
        this.logEventService = logEventService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        try {
            switch (cmd[0]) {
                case "create" -> {
                    var id = Long.valueOf(cmd[1]);
                    if (logEventService.contains(id)) {
                        System.out.println("Событие с таким id уже существует.");
                        return;
                    }
                    logEventService.createLogEvent(
                            id,
                            LogEvent.Level.valueOf(cmd[2].toUpperCase()),
                            LocalDateTime.parse(cmd[3]),
                            cmd[4]);
                    System.out.println("Событие успешно добавлено в журнал.");
                }
                case "read" -> {
                    var id = Long.valueOf(cmd[1]);
                    if (!logEventService.contains(id)) {
                        System.out.println("События с таким id не существует.");
                        return;
                    }
                    var logEvent = logEventService.findById(id);
                    System.out.printf("ID: %d, LEVEL: %s, TIMESTAMP: %s, SOURCE: %s\n",
                            logEvent.getId(), logEvent.getLevel().toString(),
                            logEvent.getTimestamp().toString(), logEvent.getSource());
                }
                case "update" -> {
                    var id = Long.valueOf(cmd[1]);
                    if (!logEventService.contains(id)) {
                        System.out.println("События с таким id не существует.");
                        return;
                    }
                    logEventService.updateLevel(id, LogEvent.Level.valueOf(cmd[2].toUpperCase()));
                    System.out.println("Событие успешно обновлено в журнале.");
                }
                case "delete" -> {
                    var id = Long.valueOf(cmd[1]);
                    if (!logEventService.contains(id)) {
                        System.out.println("События с таким id не существует.");
                        return;
                    }
                    logEventService.deleteById(id);
                    System.out.println("Событие успешно удалено из журнала.");
                }
                case "all" -> {
                    var allEvents = logEventService.getAllEvents();
                    if (allEvents.isEmpty()) {
                        System.out.println("Журнал событий пуст");
                        return;
                    }
                    for(var event : allEvents) {
                        System.out.printf("ID: %d, LEVEL: %s, TIMESTAMP: %s, SOURCE: %s\n",
                                event.getId(), event.getLevel().toString(),
                                event.getTimestamp().toString(), event.getSource());
                    }
                }
                default -> System.out.println("Введена неизвестная команда...");
            }
        } catch (Exception ex) {
            System.out.println("Ошибка при обработке команды. Попробуйте ввести заново...");
        }
    }
}
