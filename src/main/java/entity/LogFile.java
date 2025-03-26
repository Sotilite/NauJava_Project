package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "LogFiles")
public class LogFile {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Application application;

    @Column(unique = true)
    private String name;

    @Column
    private LocalDateTime uploadTime;

    @Column
    private double size;

    public LogFile() {}

    public LogFile(Application application, String name, LocalDateTime uploadTime, double size) {
        this.application = application;
        this.name = name;
        this.uploadTime = uploadTime;
        this.size = size;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}
