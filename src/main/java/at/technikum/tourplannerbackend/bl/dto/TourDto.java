package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourDto {
    private UUID id;
    private String name;
    private String from;
    private String to;
    private String transportType;
    private Double distance;
    private Long estimatedTime;
    private String description;
    private List<LogDto> logs;
}
