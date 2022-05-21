package at.technikum.tourplannerbackend.dal.entity;

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
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "DATE", nullable = false)
    private Long date;

    @Column(name = "DURATION", nullable = false)
    private Long duration;

    @Column(name = "DISTANCE", nullable = false)
    private Long distance;

    @ManyToOne
    @JoinColumn(name ="TOUR_ID", nullable = false)
    private Tour tourReference;

}

