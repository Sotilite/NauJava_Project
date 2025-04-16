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

    static public final String table =
            "<table border=\"1\" style=\"margin: auto; width: 70%\"> " +
                    "<tr><th colspan=\"5\">Application list</th></tr>" +
                    "<tr><th>ID</th><th>Name</th><th>Location</th><th>Version</th><th>Owner</th></tr>" +
                    "{{appList}}" +
                    "<tr><th colspan=\"4\" style=\"text-align: left;\">Calculation time (mls)</th><th>{{appListTime}}</th></tr>" +
                    "<tr><th colspan=\"5\">User count</th></tr>" +
                    "<tr><td colspan=\"4\" style=\"text-align: left;\">Number of users</td><td>{{userNumber}}</td></tr>" +
                    "<tr><th colspan=\"4\" style=\"text-align: left;\">Calculation time (mls)</th><th>{{userCountTime}}</th></tr>" +
                    "<tr><th colspan=\"5\">General metrics</th></tr>" +
                    "<tr><th colspan=\"4\" style=\"text-align: left;\">Calculation time (mls)</th><th>{{generalTime}}</th></tr>" +
            "</table>";
}
