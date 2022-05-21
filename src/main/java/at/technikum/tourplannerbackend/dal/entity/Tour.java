package at.technikum.tourplannerbackend.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TOUR")
public class Tour {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "START_FROM", nullable = false)
    private String from;

    @Column(name = "DESTINATION_TO", nullable = false)
    private String to;

    @Column(name = "TRANSPORT_TYPE", nullable = false)
    private TransportType transportType;

    @Column(name = "DISTANCE", nullable = false)
    private Double distance;

    @Column(name = "ESTIMATED_TIME", nullable = false)
    private Long estimatedTime;

    @Column(name = "IMAGE_PATH", nullable = false)
    private String imagePath;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "tourReference", cascade = CascadeType.ALL)
    private List<Log> logsList = new ArrayList<>();

}

