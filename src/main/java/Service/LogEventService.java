package Service;

import Core.LogEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface LogEventService {
    void createLogEvent(Long id, LogEvent.Level level, LocalDateTime timestamp, String source);
    LogEvent findById(Long id);
    void updateLevel(Long id, LogEvent.Level level);
    void deleteById(Long id);
    boolean contains(Long id);
    List<LogEvent> getAll();
}
