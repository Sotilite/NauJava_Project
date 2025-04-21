package ru.alexander.NauJava.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Reports")
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String status;

    @Column(length = 2048)
    private String content;

    public Report() {}

    public Report(String status, String content) {
        this.status = status;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
