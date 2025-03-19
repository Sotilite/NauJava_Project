package Core;

import java.time.LocalDateTime;

public class LogEvent {
    private Long id;
    private Level level;
    private LocalDateTime timestamp;
    private String source;

    public enum Level {
        INFO,
        WARNING,
        ERROR,
    }

    public LogEvent() {}

    public LogEvent(Long id, Level level, LocalDateTime timestamp, String source) {
        this.id = id;
        this.level = level;
        this.timestamp = timestamp;
        this.source = source;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return this.source;
    }
}
