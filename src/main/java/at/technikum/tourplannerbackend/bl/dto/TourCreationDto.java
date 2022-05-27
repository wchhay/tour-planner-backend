package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.RouteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourCreationDto {

    @NotBlank
    private String name;

    @NotBlank
    private String from;

    @NotBlank
    private String to;

    private RouteType routeType;

    private String description;
}
