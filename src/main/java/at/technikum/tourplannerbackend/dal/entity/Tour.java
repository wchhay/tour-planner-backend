package at.technikum.tourplannerbackend.dal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TOUR")
public class Tour {

    @Id
    @GeneratedValue
    private UUID id;

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

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @OneToMany(mappedBy = "tourReference", cascade = CascadeType.ALL)
    private List<Log> logsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;
        return id != null && Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

