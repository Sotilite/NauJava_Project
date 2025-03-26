package entity;

import jakarta.persistence.*;

import java.lang.reflect.Array;

@Entity
@Table(name = "Applications")
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column(unique = true)
    private String name;

    @Column
    private String pathToApplication;

    @Column
    private String version;

    public Application() {}

    public Application(User user, String name, String pathToApplication, String version) {
        this.user = user;
        this.name = name;
        this.pathToApplication = pathToApplication;
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPathToApplication(String pathToApplication) {
        this.pathToApplication = pathToApplication;
    }

    public String getPathToApplication() {
        return pathToApplication;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
