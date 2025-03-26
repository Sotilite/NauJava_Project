package repository;

import entity.LogEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogEventRepository extends CrudRepository<LogEvent, Long> {
    /**
     * Находит логи по диапазону времени
     * @param startTime начало отрезка времени
     * @param endTime конец отрезка времени
     * */
    List<LogEvent> findLogEventsByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Находит логи конкретного уровня
     * @param levelName наименование уровня
     * */
    List<LogEvent> findLogEventsByLevel(String levelName);
}
