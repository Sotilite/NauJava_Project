package Service;

import Core.LogEvent;
import DataAccess.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogEventServiceImpl implements LogEventService {
    private final EventRepository eventRepository;

    @Autowired
    public LogEventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createLogEvent(Long id, LogEvent.Level level, LocalDateTime timestamp, String source) {
        var logEvent = new LogEvent();
        logEvent.setId(id);
        logEvent.setLevel(level);
        logEvent.setTimestamp(timestamp);
        logEvent.setSource(source);
        eventRepository.create(logEvent);
    }

    @Override
    public LogEvent findById(Long id) {
        return eventRepository.read(id);
    }

    @Override
    public void updateLevel(Long id, LogEvent.Level level) {
        var oldLogEvent = eventRepository.read(id);
        var newLogEvent = new LogEvent();
        newLogEvent.setId(id);
        newLogEvent.setLevel(level);
        newLogEvent.setTimestamp(oldLogEvent.getTimestamp());
        newLogEvent.setSource(oldLogEvent.getSource());
        eventRepository.update(newLogEvent);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(id);
    }

    public boolean contains(Long id) {
        return eventRepository.contains(id);
    }

    public List<LogEvent> getAll() {
        return eventRepository.getAll();
    }
}
