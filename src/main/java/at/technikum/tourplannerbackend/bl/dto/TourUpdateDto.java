package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.TransportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourUpdateDto {
    private String name;
    private String from;
    private String to;
    private TransportType transportType;
    private String description;
}
