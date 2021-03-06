package at.technikum.tourplannerbackend.bl.dto;

import at.technikum.tourplannerbackend.dal.entity.TransportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private TransportType transportType;

    private String description;
}
