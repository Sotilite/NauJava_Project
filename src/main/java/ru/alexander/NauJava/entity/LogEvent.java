package ru.alexander.NauJava.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "LogEvents")
public class LogEvent {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private LogFile logFile;

    @OneToOne
    private Level level;

    @Column
    private LocalDateTime timestamp;

    @Column
    private String message;

    public LogEvent() {}

    public LogEvent(LogFile logFile, Level level, LocalDateTime timestamp, String message) {
        this.logFile = logFile;
        this.level = level;
        this.timestamp = timestamp;
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setLogFile(LogFile logFile) {
        this.logFile = logFile;
    }

    public LogFile getLogFile() {
        return logFile;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
