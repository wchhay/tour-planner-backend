package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogCreationDto {

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long totalTime;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer difficulty;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer rating;

    private String comment;
}
