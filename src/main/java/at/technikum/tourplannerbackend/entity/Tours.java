package at.technikum.tourplannerbackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
    @Table(name = "tours")
    public class Tours {

        @Id
        @GeneratedValue
        private Long id;

        @Column(name = "route", nullable = false)
        private String route;

        @Column(name = "description")
        private String description;

        @OneToMany(mappedBy = "logsReference", cascade = CascadeType.ALL)
        private List<Logs> logsList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Logs> getLogsList() {
        return logsList;
    }

    public void setLogsList(List<Logs> logsList) {
        this.logsList = logsList;
    }

    }

