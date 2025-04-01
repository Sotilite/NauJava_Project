package DataAccess;

import Core.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EventRepository implements CrudRepository<LogEvent, Long> {
    private final Map<Long, LogEvent> logEvents;

    @Autowired
    public EventRepository(Map<Long, LogEvent> logEvents) {
        this.logEvents = logEvents;
    }

    @Override
    public void create(LogEvent logEvent) {
        logEvents.put(logEvent.getId(), logEvent);
    }

    @Override
    public LogEvent read(Long id) {
        return logEvents.get(id);
    }

    @Override
    public void update(LogEvent logEvent) {
        logEvents.replace(logEvent.getId(), logEvent);
    }

    @Override
    public void delete(Long id) {
        logEvents.remove(id);
    }

    public boolean contains(Long id) {
        return logEvents.containsKey(id);
    }

    public List<LogEvent> getAll() {
        return logEvents.values().stream().toList();
    }
}
