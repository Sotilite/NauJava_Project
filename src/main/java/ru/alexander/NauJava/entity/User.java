package ru.alexander.NauJava.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @ManyToOne
    private Role role;

    @Column
    private LocalDateTime creationDate;

    @OneToMany
    private List<Application> applications;

    public User() {}

    public User(String name, String login, String password, Role role, LocalDateTime creationDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    public List<Application> getApplications() {
        return applications;
    }
}

