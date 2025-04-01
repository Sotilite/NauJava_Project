package ru.alexander.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.alexander.NauJava.entity.LogEvent;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(path = "logEvents")
public interface LogEventRepository extends CrudRepository<LogEvent, Long> {
    /**
     * Находит логи по диапазону времени
     * @param startTime начало отрезка времени
     * @param endTime конец отрезка времени
     * */
    List<LogEvent> findAllByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * Находит логи конкретного уровня по его названию
     * @param levelTitle название уровня лога
     * */
    @Query(value = "FROM LogEvent event WHERE event.level.title = :levelTitle")
    List<LogEvent> findAllByLevelTittle(String levelTitle);
}
