package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {
    private String name;
    private String from;
    private String to;
    private String transportType;
    private Double distance;
    private Long estimatedTime;
    private String description;
    private List<String> logs;
}
