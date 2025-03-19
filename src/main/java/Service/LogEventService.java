package Service;

import Core.LogEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface LogEventService {
    void createLogEvent(Long id, LogEvent.Level level, LocalDateTime timestamp, String source);
    LogEvent findById(Long id);
    void updateLevel(Long id, LogEvent.Level level);
    void deleteById(Long id);
}
