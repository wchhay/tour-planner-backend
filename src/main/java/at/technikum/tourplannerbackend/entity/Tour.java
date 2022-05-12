package at.technikum.tourplannerbackend.entity;

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
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "route", nullable = false)
    private String route;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tourReference", cascade = CascadeType.ALL)
    private List<Log> logsList = new ArrayList<>();

}

