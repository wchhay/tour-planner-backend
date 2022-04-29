package at.technikum.tourplannerbackend.tour;

import lombok.Data;

import java.util.UUID;

@Data
public class Tour {
    private UUID id;
    private String name;
}
