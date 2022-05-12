package at.technikum.tourplannerbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "log")
public class Log {

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

}

