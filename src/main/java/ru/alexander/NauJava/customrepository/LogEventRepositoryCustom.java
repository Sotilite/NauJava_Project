package ru.alexander.NauJava.customrepository;

import ru.alexander.NauJava.entity.LogEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface LogEventRepositoryCustom {
    /**
     * Находит логи по диапазону времени
     * @param startTime начало отрезка времени
     * @param endTime конец отрезка времени
     * */
    List<LogEvent> findAllByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
}
