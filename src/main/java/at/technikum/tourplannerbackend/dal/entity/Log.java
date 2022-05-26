package at.technikum.tourplannerbackend.dal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "LOG")
public class Log {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @Column(name = "COMMENT", length = 1000)
    private String comment;

    @Column(name = "TOTAL_TIME", nullable = false)
    private Long totalTime;

    @Column(name = "DIFFICULTY", nullable = false, length = 2)
    private Integer difficulty;

    @Column(name = "RATING", nullable = false, length = 2)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name ="TOUR_ID", nullable = false)
    private Tour tourReference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Log log = (Log) o;
        return id != null && Objects.equals(id, log.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

