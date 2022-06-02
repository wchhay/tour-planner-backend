package at.technikum.tourplannerbackend.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogUpdateDto {
    private LocalDateTime date;

    private Long totalTime;

    @Min(0)
    @Max(10)
    private Integer difficulty;

    @Min(0)
    @Max(10)
    private Integer rating;

    private String comment;
}
