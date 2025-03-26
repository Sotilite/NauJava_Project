package customrepository;

import entity.LogEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface LogEventRepositoryCustom {
    /**
     * Находит логи по диапазону времени
     * @param startTime начало отрезка времени
     * @param endTime конец отрезка времени
     * */
    List<LogEvent> findLogEventsByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
}
