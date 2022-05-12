package at.technikum.tourplannerbackend.entity;

import at.technikum.tourplannerbackend.tour.Tour;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class Logs {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date", nullable = false)
    private Long date;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @Column(name = "distance", nullable = false)
    private Long distance;

    @ManyToOne
    @JoinColumn(name ="tour_id", nullable = false)
    private Tour tourReference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Tour getTourReference() {
        return tourReference;
    }

    public void setTourReference(Tour tourReference) {
        this.tourReference = tourReference;
    }

}

