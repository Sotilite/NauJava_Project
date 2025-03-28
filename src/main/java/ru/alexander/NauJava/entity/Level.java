package ru.alexander.NauJava.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Levels")
public class Level {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private String description;

    public Level() {}

    public Level(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
